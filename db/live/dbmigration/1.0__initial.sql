-- apply changes
create table address (
  id                            bigint auto_increment not null,
  country                       integer,
  state                         integer,
  city                          varchar(255),
  addressLine1                  varchar(255) default '',
  addressLine2                  varchar(255) default '',
  postCode                      varchar(255) default '',
  addressType                   varchar(8),
  userDetailId                  bigint,
  constraint pk_address primary key (id)
);

create table confirmation_tokens (
  id                            bigint auto_increment not null,
  token                         varchar(255) not null,
  type                          varchar(14) not null,
  expiresAt                     datetime not null,
  confirmedAt                   datetime,
  userId                        bigint,
  createdAt                     datetime not null,
  constraint pk_confirmation_tokens primary key (id)
);

create table license_types (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  description                   varchar(255) default '',
  access                        varchar(255) default '',
  status                        varchar(8),
  createdAt                     datetime default '2020-04-26 00:00' not null,
  updatedAt                     datetime default '2020-04-26 00:00' not null,
  constraint pk_license_types primary key (id)
);

create table provider_accounts (
  id                            bigint auto_increment not null,
  userId                        bigint,
  provider                      varchar(255) default '',
  profileLink                   varchar(255) default '',
  constraint pk_provider_accounts primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  username                      varchar(255) default '',
  email                         varchar(255),
  password                      varchar(255),
  isVerified                    tinyint(1) default 0 not null,
  locked                        tinyint(1) default 0 not null,
  status                        varchar(8),
  licenseTypeId                 bigint,
  userRoleId                    bigint,
  constraint pk_users primary key (id)
);

create table user_details (
  id                            bigint auto_increment not null,
  profilePicture                varchar(255),
  firstName                     varchar(255) default '',
  otherNames                    varchar(100),
  lastName                      varchar(100),
  gender                        varchar(7),
  dateOfBirth                   datetime default '2020-04-26 00:00',
  placeOfBirth                  varchar(255) default '',
  prefix                        varchar(255) default '',
  title                         varchar(100),
  memo                          TEXT,
  lastLogin                     datetime default '2020-04-26 00:00',
  userId                        bigint,
  createdAt                     datetime default '2020-04-26 00:00' not null,
  updatedAt                     datetime default '2020-04-26 00:00' not null,
  constraint pk_user_details primary key (id)
);

create table user_permissions (
  id                            bigint auto_increment not null,
  name                          varchar(255) default '',
  code                          varchar(255) default '',
  description                   varchar(255) default '',
  status                        varchar(8) default 'disabled',
  createdAt                     datetime default '2020-04-26 00:00' not null,
  updatedAt                     datetime default '2020-04-26 00:00' not null,
  constraint pk_user_permissions primary key (id)
);

create table user_roles (
  id                            bigint auto_increment not null,
  name                          varchar(255) default '',
  description                   TEXT,
  capabilities                  TEXT,
  status                        varchar(8),
  createdAt                     datetime default '2020-04-26 00:00' not null,
  updatedAt                     datetime default '2020-04-26 00:00' not null,
  constraint pk_user_roles primary key (id)
);

create table user_role_permissions (
  id                            bigint auto_increment not null,
  roleId                        bigint,
  permissionId                  bigint,
  constraint pk_user_role_permissions primary key (id)
);

create index ix_address_userDetailId on address (userDetailId);
alter table address add constraint fk_address_userDetailId foreign key (userDetailId) references user_details (id) on delete restrict on update restrict;

create index ix_confirmation_tokens_userId on confirmation_tokens (userId);
alter table confirmation_tokens add constraint fk_confirmation_tokens_userId foreign key (userId) references users (id) on delete restrict on update restrict;

create index ix_provider_accounts_userId on provider_accounts (userId);
alter table provider_accounts add constraint fk_provider_accounts_userId foreign key (userId) references users (id) on delete restrict on update restrict;

create index ix_users_licenseTypeId on users (licenseTypeId);
alter table users add constraint fk_users_licenseTypeId foreign key (licenseTypeId) references license_types (id) on delete restrict on update restrict;

create index ix_users_userRoleId on users (userRoleId);
alter table users add constraint fk_users_userRoleId foreign key (userRoleId) references user_roles (id) on delete restrict on update restrict;

create index ix_user_details_userId on user_details (userId);
alter table user_details add constraint fk_user_details_userId foreign key (userId) references users (id) on delete restrict on update restrict;

create index ix_user_role_permissions_roleId on user_role_permissions (roleId);
alter table user_role_permissions add constraint fk_user_role_permissions_roleId foreign key (roleId) references user_roles (id) on delete restrict on update restrict;

create index ix_user_role_permissions_permissionId on user_role_permissions (permissionId);
alter table user_role_permissions add constraint fk_user_role_permissions_permissionId foreign key (permissionId) references user_permissions (id) on delete restrict on update restrict;

