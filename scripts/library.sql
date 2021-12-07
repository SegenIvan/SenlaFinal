drop database if exists senlafinal;
create database senlafinal;
USE senlafinal ;

create table announcement_state (
                                    announcement_id bigint not null,
                                    state_id bigint not null,
                                    primary key (announcement_id, state_id)
) engine=InnoDB;

create table announcements (
                               id bigint not null auto_increment,
                               date_of_create date,
                               date_of_payment date,
                               tag varchar(255),
                               text varchar(255),
                               user_id bigint,
                               is_active bit,
                               primary key (id)
) engine=InnoDB;

create table comments (
                          id bigint not null auto_increment,
                          date_of_create date,
                          message varchar(255),
                          announcement_id bigint,
                          user_id bigint,
                          primary key (id)
) engine=InnoDB;

create table credentials_roles (
                                   credential_id bigint not null,
                                   role_id bigint not null,
                                   primary key (credential_id, role_id)
) engine=InnoDB;

create table messages (
                          id bigint not null auto_increment,
                          date_sent date,
                          is_read bit,
                          text varchar(255),
                          recipient_id bigint,
                          sender_id bigint,
                          primary key (id)
) engine=InnoDB;

create table refreshtoken (
                              id bigint not null auto_increment,
                              expiry_date datetime(6) not null,
                              token varchar(255) not null,
                              user_id bigint,
                              primary key (id)
) engine=InnoDB;

create table roles (
                       id bigint not null auto_increment,
                       name varchar(20),
                       primary key (id)
) engine=InnoDB;

create table states (
                        id bigint not null auto_increment,
                        name varchar(20),
                        primary key (id)
) engine=InnoDB;

create table users (
                       id bigint not null auto_increment,
                       email varchar(255),
                       name varchar(255),
                       phone varchar(255),
                       rating integer,
                       credential_id bigint,
                       primary key (id)
) engine=InnoDB;

create table credentials (
                             id bigint not null auto_increment,
                             is_active bit,
                             login varchar(255),
                             password varchar(255),
                             primary key (id)
) engine=InnoDB;
create index tagIndex on announcements (tag);

alter table refreshtoken
    add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token);

alter table announcement_state
    add constraint FK89cvg8exlo70a5bqk96ajb29u
        foreign key (state_id)
            references states (id);

alter table announcement_state
    add constraint FKs4syekw1l8efpr23xbhwf4xk6
        foreign key (announcement_id)
            references announcements (id);

alter table announcements
    add constraint FKlfxjojfcdhlx73ofpifkc6j92
        foreign key (user_id)
            references users (id);

alter table comments
    add constraint FK8vsy5fhwxwlqdd41xre4om77b
        foreign key (announcement_id)
            references announcements (id);

alter table comments
    add constraint FK8omq0tc18jd43bu5tjh6jvraq
        foreign key (user_id)
            references users (id);

alter table credentials_roles
    add constraint FKrklwpb8s4ra4q6gnk0nnpl3rh
        foreign key (role_id)
            references roles (id);

alter table credentials_roles
    add constraint FK4xv00v7o6ckw8kp3n900q0ca4
        foreign key (credential_id)
            references credentials (id);

alter table messages
    add constraint FKhdkwfnspwb3s60j27vpg0rpg6
        foreign key (recipient_id)
            references users (id);

alter table messages
    add constraint FK4ui4nnwntodh6wjvck53dbk9m
        foreign key (sender_id)
            references users (id);

alter table refreshtoken
    add constraint FKa652xrdji49m4isx38pp4p80p
        foreign key (user_id)
            references users (id);

alter table users
    add constraint FKtq506ygx6ic0s2by4a19snp2w
        foreign key (credential_id)
            references credentials (id)