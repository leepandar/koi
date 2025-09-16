package com.koi.common.robot.message.push;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.koi.common.robot.RobotProperties;
import com.koi.common.robot.emums.NotifyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 钉钉机器人
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class DingTalkRobotMessageHandler implements RobotMessageHandler {

    private final RobotProperties robotProperties;

    @Override
    public String notify(String message) {
        Map<String, Object> body = Map.ofEntries(
                Map.entry("msgtype", "text"),
                Map.entry("at", Map.of("isAtAll", true)),
                Map.entry("text", Map.of("content", message)));
        String response = this.request(body);
        log.info("ding talk notify response - {}", response);
        return response;
    }

    @Override
    public String notify(String message, Map<?, ?> map, boolean ignoreNull) {
        return notify(StrFormatter.format(message, map, ignoreNull));
    }

    @Override
    public String getUrl() {
        RobotProperties.DingTalk dingTalk = robotProperties.getDingTalk();
        StringBuilder url = new StringBuilder();
        url.append("https://oapi.dingtalk.com/robot/send?access_token=");
        url.append(dingTalk.getAccessToken());
        String secret = dingTalk.getSecret();
        if (StrUtil.isBlank(secret)) {
            return url.toString();
        }
        Long timestamp = System.currentTimeMillis();
        url.append("&timestamp=").append(timestamp);
        String sign = SecureUtil.hmacSha256(secret).digestHex(timestamp + "\n" + secret);
        url.append("&sign=").append(URLEncoder.encode(sign, StandardCharsets.UTF_8));
        return url.toString();
    }

    @Override
    public NotifyType notifyType() {
        return NotifyType.DING_TALK;
    }
}
