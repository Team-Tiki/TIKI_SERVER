package com.tiki.server.folder.entity;

import static com.tiki.server.document.message.ErrorCode.INVALID_AUTHORIZATION;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.document.exception.DocumentException;

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

	public void validateTeamId(final long teamId) {
		if (this.teamId != teamId) {
			throw new DocumentException(INVALID_AUTHORIZATION);
		}
	}

	public String getChildPath() {
		return path + "/" + id;
	}

	private String generatePath(Folder parentFolder) {
		if (parentFolder == null) {
			return "";
		}
		return parentFolder.getPath() + "/" + parentFolder.getId();
	}
}
