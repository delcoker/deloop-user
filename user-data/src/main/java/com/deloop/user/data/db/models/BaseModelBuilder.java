//package com.deloop.user.data.db.models;
//
//import io.ebean.annotation.DbDefault;
//import io.ebean.annotation.WhenCreated;
//import io.ebean.annotation.WhenModified;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import java.time.LocalDateTime;
//
//
//public static class BaseModelBuilder {
//
//    @Id
//    long id;
//
//    @Column
//    @WhenCreated
//    @DbDefault("2020-04-26 00:00")
//    LocalDateTime createdAt;
//
//    @Column
//    @WhenModified
//    @DbDefault("2020-04-26 00:00")
//    LocalDateTime updatedAt;
//
//    public BaseModelBuilder(long id) {
//        this.id = id;
//    }
//
////    public BaseModelBuilder(LocalDateTime createdAt) {
////        this.createdAt = createdAt;
////    }
//
//    public BaseModelBuilder(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public BaseModel build() {
//        BaseModel baseModel = new BaseModel(this);
//    }
//}
