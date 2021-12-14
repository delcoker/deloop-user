-- apply changes
create table confirmation_tokens (
  id                            bigint auto_increment not null,
  token                         varchar(255) not null,
  expiresAt                     datetime not null,
  userId                        bigint,
  createdAt                     datetime not null,
  confirmedAt                   datetime default '2020-04-26 00:00' not null,
  constraint pk_confirmation_tokens primary key (id)
);

create index ix_confirmation_tokens_userId on confirmation_tokens (userId);
alter table confirmation_tokens add constraint fk_confirmation_tokens_userId foreign key (userId) references users (id) on delete restrict on update restrict;

