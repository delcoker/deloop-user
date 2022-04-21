alter table address drop foreign key fk_address_userDetailId;
drop index ix_address_userDetailId on address;

alter table confirmation_tokens drop foreign key fk_confirmation_tokens_userId;
drop index ix_confirmation_tokens_userId on confirmation_tokens;

alter table provider_accounts drop foreign key fk_provider_accounts_userId;
drop index ix_provider_accounts_userId on provider_accounts;

alter table users drop foreign key fk_users_licenseTypeId;
drop index ix_users_licenseTypeId on users;

alter table users drop foreign key fk_users_userRoleId;
drop index ix_users_userRoleId on users;

alter table user_details drop foreign key fk_user_details_userId;
drop index ix_user_details_userId on user_details;

alter table user_role_permissions drop foreign key fk_user_role_permissions_roleId;
drop index ix_user_role_permissions_roleId on user_role_permissions;

alter table user_role_permissions drop foreign key fk_user_role_permissions_permissionId;
drop index ix_user_role_permissions_permissionId on user_role_permissions;

drop table if exists address;

drop table if exists confirmation_tokens;

drop table if exists license_types;

drop table if exists provider_accounts;

drop table if exists users;

drop table if exists user_details;

drop table if exists user_permissions;

drop table if exists user_roles;

drop table if exists user_role_permissions;

