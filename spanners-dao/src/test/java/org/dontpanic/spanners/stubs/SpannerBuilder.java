package org.dontpanic.spanners.stubs;

import org.dontpanic.spanners.dao.Spanner;

/**
 * Builder for spanner test data
 * User: Stevie
 * Date: 05/11/14
 */
public class SpannerBuilder {

	public static final int DEFAULT_ID = 1;
	public static final String DEFAULT_NAME = "Bertha";
	public static final int DEFAULT_SIZE = 16;
	public static final String DEFAULT_OWNER = "Mr Smith";

	private int id = DEFAULT_ID;
	private String name = DEFAULT_NAME;
	private int size = DEFAULT_SIZE;
	private String owner = DEFAULT_OWNER;

	public static SpannerBuilder aTestSpanner() {
		return new SpannerBuilder();
	}

	public SpannerBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public SpannerBuilder named(String name) {
		this.name = name;
		return this;
	}

	public SpannerBuilder withSize(int size) {
		this.size = size;
		return this;
	}

	public SpannerBuilder ownedBy(String owner) {
		this.owner = owner;
		return this;
	}

	public Spanner build() {
		Spanner spanner = new Spanner();
		spanner.setId(this.id);
		spanner.setName(this.name);
		spanner.setSize(this.size);
		spanner.setOwner(this.owner);
		return spanner;
	}
}
