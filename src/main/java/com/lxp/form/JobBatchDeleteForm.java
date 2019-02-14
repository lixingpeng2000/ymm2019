package com.lxp.form;

import java.util.List;

public class JobBatchDeleteForm {
	
	private List jno;

	public List getJno() {
		return jno;
	}

	public void setJno(List jno) {
		this.jno = jno;
	}

	public JobBatchDeleteForm(List jno) {
		super();
		this.jno = jno;
	}

	public JobBatchDeleteForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JobBatchDeleteForm [jno=" + jno + "]";
	}
	

}
