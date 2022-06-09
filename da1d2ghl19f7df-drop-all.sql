alter table if exists address drop constraint if exists fk_address_userDetailId;
drop index if exists ix_address_userDetailId;

alter table if exists confirmation_tokens drop constraint if exists fk_confirmation_tokens_userId;
drop index if exists ix_confirmation_tokens_userId;

alter table if exists provider_accounts drop constraint if exists fk_provider_accounts_userId;
drop index if exists ix_provider_accounts_userId;

alter table if exists users drop constraint if exists fk_users_licenseTypeId;
drop index if exists ix_users_licenseTypeId;

alter table if exists users drop constraint if exists fk_users_userRoleId;
drop index if exists ix_users_userRoleId;

alter table if exists user_details drop constraint if exists fk_user_details_userId;
drop index if exists ix_user_details_userId;

alter table if exists user_role_permissions drop constraint if exists fk_user_role_permissions_roleId;
drop index if exists ix_user_role_permissions_roleId;

alter table if exists user_role_permissions drop constraint if exists fk_user_role_permissions_permissionId;
drop index if exists ix_user_role_permissions_permissionId;

drop table if exists address cascade;

drop table if exists confirmation_tokens cascade;

drop table if exists license_types cascade;

drop table if exists provider_accounts cascade;

drop table if exists users cascade;

drop table if exists user_details cascade;

drop table if exists user_permissions cascade;

drop table if exists user_roles cascade;

drop table if exists user_role_permissions cascade;

