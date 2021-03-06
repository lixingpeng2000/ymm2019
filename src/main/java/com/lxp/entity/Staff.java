package com.lxp.entity;

public class Staff {
    private String id;
    private String name;
    private Integer age;
 
    public Staff() {
        super();
    }
 
    public Staff(String id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
   
}

