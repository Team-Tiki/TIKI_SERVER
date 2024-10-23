package com.tiki.server.folder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.folder.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/folders")
public class FolderController {

	private final FolderService folderService;

	/*
	폴더 생성하기
	 */
}
