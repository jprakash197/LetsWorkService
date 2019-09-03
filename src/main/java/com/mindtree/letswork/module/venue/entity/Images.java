package com.mindtree.letswork.module.venue.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	public Images() {

	}

	public Images(byte[] image) {
		super();
		this.image = image;

	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + imageId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Images other = (Images) obj;
		if (!Arrays.equals(image, other.image))
			return false;
		if (imageId != other.imageId)
			return false;
		return true;
	}

}
