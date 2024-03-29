package com.ict.ajax.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KakaoController {
	
	// 인증 코드를 받기 위해서 redirect_url 사용한다
	@RequestMapping("/kakaologin.do")
	public ModelAndView KakaoLogin(String code) {
		ModelAndView mv = new ModelAndView("result3");
		// 토큰 받기
//		https://kauth.kakao.com/oauth/token
		String  reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			// post 요청에 필요
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// 요청 시작 부분 (헤더)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// 본문에 필요한 요구사항 4개
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=2f6a866a0f9d707e2f98472afa68c08a");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code="+code);
			bw.write(sb.toString());
			bw.flush();
			
			// 성공하면 200
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				// 요청을 총해 얻은 json타입의 결과 메세지를 읽어온다
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer result = new StringBuffer();
				String line = null;
				while((line = br.readLine())!= null) {
					result.append(line);
				}
				// json 파싱하기 access_token, refresh_token 을 잡아내서 카카오 API 요청을 한 후
				// MOdelAndView에 저장해서 result3.jsp에서 결과를 나타낸다
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(result.toString());
				JSONObject json = (JSONObject)obj;
				
				String access_token = (String)json.get("access_token");
				String refresh_token = (String)json.get("refresh_token");
				
				// 사용자 정보 요청하기
				// GET/POST https://kapi.kakao.com/v2/user/me
				String apiURL = "https://kapi.kakao.com/v2/user/me";
				
				// 헤더
				String header = "Bearer " + access_token;
				URL url2 = new URL(apiURL);
				HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();
				
				// POST
				conn2.setRequestMethod("POST");
				conn2.setDoOutput(true);
				
				// 헤더 요청 사항
				conn2.setRequestProperty("Authorization", header);
				
				int res_code = conn2.getResponseCode();
				// HttpURLConnection.HTTP_OK 요청 성공 시 200
				if(res_code == HttpURLConnection.HTTP_OK) {
					// 카카오 서버쪽에서 사용자의 정보를 보낸다
					// 이거를 읽어와서 필요한 정보들을 선별한다
					BufferedReader brdm = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
					String str = null;
					StringBuffer res = new StringBuffer();
					while ((str = brdm.readLine())!= null) {
						res.append(str);
					}
					// 사용자 정보
					json = (JSONObject)parser.parse(res.toString());
					
					System.out.println(res.toString());
					
					JSONObject props = (JSONObject)json.get("properties");
					String nickName = (String)props.get("nickname");
					String profile_image = (String)props.get("profile_image");
					
					JSONObject kakao_account = (JSONObject)json.get("kakao_account");
					String email = (String)kakao_account.get("email");
					
					mv.addObject("nickName",nickName);
					mv.addObject("email",email);
					mv.addObject("profile_image",profile_image);
				}
			}
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// 카카오 지도는 REST API 지원하지 않는다 (자바스크립트 사용)
	@RequestMapping("/kakaomap01.do")
	public ModelAndView KakaoMap01() {
		return new ModelAndView("kakao_map01");
	}
	
	@RequestMapping("/kakaomap02.do")
	public ModelAndView KakaoMap02() {
		return new ModelAndView("kakao_map02");
	}
	
	@RequestMapping("/kakaomap03.do")
	public ModelAndView KakaoMap03() {
		return new ModelAndView("kakao_map03");
	}
	
	@RequestMapping("/kakaomap04.do")
	public ModelAndView KakaoMap04() {
		return new ModelAndView("kakao_map04");
	}
	
	@RequestMapping("/kakaoaddr.do")
	public ModelAndView KaKaoAddr() {
		return new ModelAndView("kakao_addr");
	}
	
	@RequestMapping("/kakao_addr_ok.do")
	public ModelAndView KaKaoAddrOK(AddrVO addrvo) {
		ModelAndView mv = new ModelAndView("redirect:/");
		System.out.println(addrvo.getPostcode());
		System.out.println(addrvo.getAddress());
		if (addrvo.getDetailAddress() == null) {
			System.out.println("상세주소 없음");
		}else {
			System.out.println(addrvo.getDetailAddress());
		}
		System.out.println(addrvo.getExtraAddress());
		return mv;
	}
}
