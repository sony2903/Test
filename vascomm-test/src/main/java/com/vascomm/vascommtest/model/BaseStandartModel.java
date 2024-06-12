package com.vascomm.vascommtest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseStandartModel {

	protected Date insert_date;
	protected Integer insert_user;
	protected Date update_date;
	protected Integer update_user;
	protected Integer delete_flag;
	protected Date delete_date;
	protected Integer delete_user;
	protected Integer active_flg;

}
