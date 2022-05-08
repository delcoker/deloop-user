-- apply changes
alter table confirmation_tokens drop foreign key fk_confirmation_tokens_userId;
alter table confirmation_tokens add constraint fk_confirmation_tokens_userId foreign key (userId) references users (id) on delete cascade on update restrict;
