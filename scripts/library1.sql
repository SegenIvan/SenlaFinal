SET CHARACTER SET 'utf8';

use senlafinal;

replace into roles (id, name)
values (1, "ROLE_USER"),(2,"ROLE_MODERATOR"),(3,"ROLE_ADMIN");

replace into states (id, name)
values (1, "ACTIVE"),(2, "NOT_ACTIVE");