package com.tiki.server.timeblock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.timeblock.service.TimeBlockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/time-blocks")
public class TimeBlockController {

	private final TimeBlockService timeBlockService;
}
