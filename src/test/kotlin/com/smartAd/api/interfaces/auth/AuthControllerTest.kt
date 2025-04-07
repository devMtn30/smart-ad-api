package com.smartAd.api.interfaces.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.smartAd.api.interfaces.auth.dto.LoginRequest
import com.smartAd.api.interfaces.auth.dto.SignupRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional

/**
 * @SpringBootTest + @AutoConfigureMockMvc 로 실제 컨트롤러부터 DB까지
 * 전부 스프링 컨텍스트 안에서 테스트할 수 있습니다.
 *
 * @Transactional 을 클래스 레벨에 붙이면, 각 테스트 메서드마다
 * 트랜잭션을 열고 끝날 때 롤백해주므로 DB가 매번 초기화됩니다.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    // Jackson ObjectMapper (코틀린 모듈 등록)
    private val objectMapper = ObjectMapper().registerKotlinModule()

    @Test
    fun `회원가입 성공 테스트`() {
        // given
        val request = SignupRequest(username = "testuser", password = "testpass", email = "")
        val requestBody = objectMapper.writeValueAsString(request)

        // when & then
        mockMvc.perform(
            post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.username").value("testuser"))
        // SignupResponse의 username 필드 값 검증
    }

    @Test
    fun `로그인 성공 테스트`() {
        // 1) 사전 준비 - 회원가입
        val signUpReq = SignupRequest(username = "loginUser", password = "loginPass", email = "")
        mockMvc.perform(
            post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpReq))
        )
            .andExpect(status().isOk)

        // 2) 로그인 시도
        val loginReq = LoginRequest(username = "loginUser", password = "loginPass")
        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginReq))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.token").exists()) // 응답 JSON에 "token" 필드 존재
    }

    @Test
    fun `로그인 실패 테스트 - 비밀번호 불일치`() {
        // 1) 사전 준비 - 회원가입
        val signUpReq = SignupRequest(username = "failUser", password = "correctPass", email = "")
        mockMvc.perform(
            post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpReq))
        )
            .andExpect(status().isOk)

        // 2) 로그인시 비밀번호 틀림
        val loginReq = LoginRequest(username = "failUser", password = "wrongPass")
        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginReq))
        )
            .andExpect(status().isBadRequest)
            .andExpect(content().string("비밀번호가 일치하지 않습니다."))
    }
}