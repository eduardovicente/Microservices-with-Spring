package com.vicentemartinez.microservicelogmanagement.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

import lombok.Data;

@Data
@Table("log")
public class Log implements Serializable {

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;

	@Column("ip")
	private String ip;

	@Column("course_id")
	private Long courseId;

	@Column("log_date")
	private LocalDateTime logDate;

}
