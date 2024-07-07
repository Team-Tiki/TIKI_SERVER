package com.tiki.server.document.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.document.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/documents")
public class DocumentController {

	private final DocumentService documentService;
}
