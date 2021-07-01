package com.hst.hsttalk.core.model.protocol.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-07-02
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ChatResponse {
	private final String senderNickname;
	private final String message;
	private final String sendAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
}
