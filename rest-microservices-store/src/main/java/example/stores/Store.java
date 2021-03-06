/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.stores;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity to represent a {@link Store}.
 * 
 * @author Oliver Gierke
 */
@Document
public class Store {

	private  @Id String id;
	private  String name;
	private  Address address;

	public Store(String name, Address address) {

		this.name = name;
		this.address = address;
		this.id = null;
	}

	protected Store() {

		this.id = null;
		this.name = null;
		this.address = null;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}
}
