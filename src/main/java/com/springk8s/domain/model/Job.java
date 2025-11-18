// package com.meganote.scheduler.domain.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.meganote.scheduler.domain.constant.JobStatus;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.annotation.LastModifiedDate;
// import org.springframework.data.mongodb.core.index.Indexed;
// import org.springframework.data.mongodb.core.mapping.Document;

// import java.io.Serializable;
// import java.util.Date;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @Document(collection = "sch_jobs")
// public class Job implements Serializable {

// @Id
// @JsonIgnore
// private String id;

// @Indexed
// private String name;

// private Double progress;

// private JobStatus status;

// private String message;

// @CreatedDate
// private Date createDate;

// @LastModifiedDate
// private Date lastModifiedDate;
// }
