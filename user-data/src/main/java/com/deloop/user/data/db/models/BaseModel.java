//package com.deloop.user.data.db.models;
//
//import io.ebean.Model;
//import io.ebean.annotation.DbDefault;
//import io.ebean.annotation.WhenCreated;
//import io.ebean.annotation.WhenModified;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
////@Builder(toBuilder = true)
//@NoArgsConstructor
//@MappedSuperclass
//public class BaseModel extends Model {
//
//    @Id
//    public long id;
//
//    @Column
//    @WhenCreated
//    @DbDefault("2020-04-26 00:00")
//    public LocalDateTime createdAt;
//
//    @Column
//    @WhenModified
//    @DbDefault("2020-04-26 00:00")
//    public LocalDateTime updatedAt;
//
//    public BaseModel(BaseModelBuilder builder) {
//        this.id = builder.id;
//        this.createdAt = builder.createdAt;
//        this.updatedAt = builder.updatedAt;
//    }
//
//    //    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class BaseModelBuilder {
//
//        @Id
//        long id;
//
//        @Column
//        @WhenCreated
//        @DbDefault("2020-04-26 00:00")
//        LocalDateTime createdAt;
//
//        @Column
//        @WhenModified
//        @DbDefault("2020-04-26 00:00")
//        LocalDateTime updatedAt;
//
//        public BaseModelBuilder id(long id) {
//            this.id = id;
//            return this;
//        }
//
//        public BaseModelBuilder createdAt(LocalDateTime createdAt) {
//            this.createdAt = createdAt;
//            return this;
//        }
//
//        public BaseModelBuilder updatedAt(LocalDateTime updatedAt) {
//            this.updatedAt = updatedAt;
//            return this;
//        }
//
//        public BaseModel build() {
//            BaseModel baseModel = new BaseModel(this);
//            return baseModel;
//        }
//    }
//}
