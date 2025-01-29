package com.tiki.server.team.controller.docs;

import java.security.Principal;

import com.tiki.server.team.dto.request.TeamInformUpdateRequest;
import com.tiki.server.team.dto.request.TeamInformUpdateServiceRequest;
import com.tiki.server.team.dto.response.UsageGetResponse;
import com.tiki.server.team.dto.response.CategoriesGetResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;
import com.tiki.server.team.service.dto.response.TeamInformGetResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.team.dto.request.TeamCreateRequest;
import com.tiki.server.team.dto.response.TeamCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "teams", description = "팀 API")
public interface TeamControllerDocs {

	@Operation(
			summary = "팀 생성",
			description = "팀을 생성한다.",
			responses = {
					@ApiResponse(responseCode = "201", description = "성공"),
					@ApiResponse(responseCode = "404", description = "유효하지 않은 회원"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<TeamCreateResponse> createTeam(
			@Parameter(hidden = true) final Principal principal,
			@RequestBody final TeamCreateRequest request
	);

	@Operation(
			summary = "전체 팀 조회",
			description = "가입한 대학의 전체 팀을 조회한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(responseCode = "404", description = "유효하지 않은 회원"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<TeamsGetResponse> getAllTeams(
			@Parameter(hidden = true) final Principal principal
	);

	@Operation(
			summary = "팀 정보 조회",
			description = "특정 팀의 정보를 조회한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(responseCode = "404", description = "존재하지 않는 팀"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<TeamInformGetResponse> getTeamName(
			@Parameter(name = "teamId", description = "팀 ID", required = true, in = ParameterIn.PATH)
			@PathVariable final long teamId
	);

	@Operation(
			summary = "카테고리 조회",
			description = "카테고리 리스트를 조회한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<CategoriesGetResponse> getCategories();

	@Operation(
			summary = "팀 삭제",
			description = "팀을 삭제한다.",
			responses = {
					@ApiResponse(responseCode = "204", description = "성공"),
					@ApiResponse(responseCode = "404", description = "유효하지 않은 회원"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	void deleteTeam(
			@Parameter(hidden = true) final Principal principal,
			@Parameter(name = "teamId", description = "팀 ID", required = true, in = ParameterIn.PATH)
			@PathVariable final long teamId
	);

	@Operation(
			summary = "팀 정보 수정",
			description = "팀 정보를 수정한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(responseCode = "403", description = "권한 없음"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<?> updateTeamInform(
			@Parameter(hidden = true) final Principal principal,
			@Parameter(name = "teamId", description = "팀 ID", required = true, in = ParameterIn.PATH)
			@PathVariable final long teamId,
			@RequestBody final TeamInformUpdateRequest request
	);

	@Operation(
			summary = "관리자 권한 변경",
			description = "팀 관리자의 권한을 변경한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(responseCode = "403", description = "권한 없음"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<?> alterAdmin(
			@Parameter(hidden = true) final Principal principal,
			@Parameter(name = "teamId", description = "팀 ID", required = true, in = ParameterIn.PATH)
			@PathVariable final long teamId,
			@Parameter(name = "targetId", description = "권한 변경 대상 ID", required = true, in = ParameterIn.PATH)
			@PathVariable final long targetId
	);

	@Operation(
			summary = "팀 용량 정보 조회",
			description = "팀 용량 정보를 조회한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(responseCode = "500", description = "서버 내부 오류")
			}
	)
	SuccessResponse<UsageGetResponse> getCapacityInfo(
			@Parameter(hidden = true) final Principal principal,
			@Parameter(name = "teamId", description = "팀 ID", required = true, in = ParameterIn.PATH)
			@PathVariable final long teamId
	);
}