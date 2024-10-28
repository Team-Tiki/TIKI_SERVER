package com.tiki.server.folder.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.tiki.server.common.entity.BaseTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Folder extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	private String path;

	private long teamId;

	public Folder(String name, Folder parentFolder, long teamId) {
		this.name = name;
		this.path = generatePath(parentFolder);
		this.teamId = teamId;
	}

	private String generatePath(Folder parentFolder) {
		if (parentFolder == null) {
			return "";
		}
		return parentFolder.getPath() + "/" + parentFolder.getId();
	}
}
