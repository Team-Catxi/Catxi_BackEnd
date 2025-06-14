package com.project.catxi.common.auth.controller;

import com.project.catxi.common.api.ApiResponse;
import com.project.catxi.common.api.error.MemberErrorCode;
import com.project.catxi.common.api.exception.CatxiException;
import com.project.catxi.common.auth.kakao.KakaoDTO;
import com.project.catxi.common.auth.service.CustomOAuth2UserService;
import com.project.catxi.common.auth.service.CustomUserDetailsService;
import com.project.catxi.common.domain.MemberStatus;
import com.project.catxi.member.domain.Member;
import com.project.catxi.member.dto.CustomUserDetails;
import com.project.catxi.member.repository.MemberRepository;
import com.project.catxi.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class OAuthController {

  private final CustomOAuth2UserService customOAuth2UserService;
  private static final Set<String> usedCodes = ConcurrentHashMap.newKeySet();

  @GetMapping("/login/kakao")
  public ApiResponse<?> kakaoLogin(@RequestParam("code") String accessCode, HttpServletResponse response) {
    // 중복 코드 체크
    if (!usedCodes.add(accessCode)) {
      log.warn("🚨중복 code 요청 차단 code = {}", accessCode);
      return ApiResponse.error(MemberErrorCode.DUPLICATE_AUTHORIZE_CODE);
    }

    try {
      Member user = customOAuth2UserService.oAuthLogin(accessCode, response);

      if (user.getStatus() == MemberStatus.PENDING) {
        return ApiResponse.success("isNewUser");
      } else {
        return ApiResponse.success("로그인 성공");
      }
    } catch (Exception e) {
      log.error("[카카오 로그인 실패] code = {}, error = {}", accessCode, e.getMessage());
      // 실패한 경우 usedCodes에서 제거 (재시도 가능하게)
      usedCodes.remove(accessCode);
      return ApiResponse.error(MemberErrorCode.ACCESS_EXPIRED);
    }
  }

    // 카카오 회원가입

    // 추가 회원가입 단계
    @PatchMapping("/signUp/catxi")
    public ResponseEntity<?> completeSignup (@RequestBody @Valid KakaoDTO.CatxiSignUp dto, @AuthenticationPrincipal CustomUserDetails userDetails){

      customOAuth2UserService.catxiSignup(userDetails.getUsername(), dto);
      return ResponseEntity.ok("추가 회원정보 등록 완료");
    }

  @Operation(summary = "닉네임 중복 조회")
  @GetMapping("/signUp/catxi/checkNN")
  public ResponseEntity<?> checkNN(@RequestParam("nickname") String nickname) {
    boolean isDuplicate = customOAuth2UserService.isNNDuplicate(nickname);
    return ResponseEntity.ok(isDuplicate);
  }

  }