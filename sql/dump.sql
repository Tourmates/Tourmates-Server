insert into tourmates.member (member_id, created_date, last_modified_date, active, birth, email, gender, login_id,
                              login_pw, name, nickname, nickname_last_modified_date, tel)
values (1, '2023-05-24 02:00:24.194000', '2023-05-24 02:00:24.194000', 'ACTIVE', '2000-01-01', 'ssafy1@ssafy.com',
        'MALE', 'ssafy1', 'ssafy1111!', '임우택', '코코', '2023-05-24 02:00:24.152000', '010-1111-1111'),
       (2, '2023-05-24 02:00:24.210000', '2023-05-24 02:00:24.210000', 'ACTIVE', '2000-02-02', 'ssafy2@ssafy.com',
        'FEMALE', 'ssafy2', 'ssafy2222!', '정유빈', '광주', '2023-05-24 02:00:24.152000', '010-2222-2222');

insert into tourmates.member_roles (member_member_id, roles)
values (1, 'MEMBER'),
       (2, 'MEMBER');

insert into tourmates.admin (admin_id, created_date, last_modified_date, active, email, login_id, login_pw, name, tel)
values (3, '2023-05-24 02:00:24.227000', '2023-05-24 02:00:24.227000', 'ACTIVE', 'admin@ssafy.com', 'admin', '1!',
        '임우택', '010-0000-0000');

insert into tourmates.hot_place (hot_place_id, created_date, last_modified_date, active, content, hit, title,
                                 visited_date, content_id, member_id)
values (5, '2023-05-24 04:57:54.623000', '2023-05-24 05:00:38.865000', 'ACTIVE',
        '<p>비오는 어린이 날 롯데월드 왔습니다.<br />비가와서 그런지 실내에 사람이 엄청 많아요.<br />오늘은 카봇과 함께하는 행사도 하고<br />저는 잘 모르는 베리의 헬로토이 베리언니 롯데월드에서 아이들과 사진도 찍어주네요<br />초딩 언니는 어찌나 행복해 하는지 껑충껑충 뜁니다<br />인기가 이렇게 많은지 첨 알았어요~~~ㅎㅎㅎㅎㅎ<br />인기 많은 언니 베리?<br />내일은 애들과 에버랜드 가기로 했는데 날씨가 어쩔지 모르겠네요~~~<br />날씨보다 많은 사람 때문에 힘들겠지요ㅠ</p>',
        1, '어린이날에 아이들과 재밌게 놀다왔어요~', '2023-05-05', 126498, 1),
       (15, '2023-05-24 05:00:18.810000', '2023-05-24 05:00:26.364000', 'ACTIVE', '<p style="text-align: center;"><span style="font-size: 24pt;"><span id="SE-02f18b8c-045c-11ed-8314-1dc9b9717a39" class="se-fs-fs19 se-ff-   " style="color: #e03e2d;"><strong>싸이 흠뻑쇼 2022</strong></span><span class="se-fs- se-ff-   "> 서울 잠실 올림픽주경기장 신나게 놀고왔습니다!</span></span></p>
<p style="text-align: center;">&nbsp;</p>
<p id="SE-02f69463-045c-11ed-8314-8762003c3ca8" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f69462-045c-11ed-8314-6923fde64939" class="se-fs- se-ff-   ">더 많은 사진들이 있긴 하지만</span></p>
<p id="SE-02f69465-045c-11ed-8314-31bd53ab8c04" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f69464-045c-11ed-8314-29d46ee7e73f" class="se-fs- se-ff-   ">개인적 추억으로 남기구!</span></p>
<p id="SE-02f69467-045c-11ed-8314-f7fa502a459f" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f69466-045c-11ed-8314-1bf716e69062" class="se-fs- se-ff-   ">직접 가시는걸 강력하게 추천드립니당</span></p>
<p id="SE-02f69469-045c-11ed-8314-b7f23d0a1050" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f69468-045c-11ed-8314-2560ec17ae1b" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6946b-045c-11ed-8314-af5679e89e0b" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6946a-045c-11ed-8314-fdbc1c7ba69f" class="se-fs- se-ff-   ">싸이 콘서트는 정말 진심</span></p>
<p id="SE-02f6946d-045c-11ed-8314-d744547520e1" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6946c-045c-11ed-8314-451f7c60dc13" class="se-fs- se-ff-   ">직접 보고 느끼는게 가장 최고인듯합니다!!</span></p>
<p id="SE-02f6946f-045c-11ed-8314-833af017ffd2" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6946e-045c-11ed-8314-618089e67520" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f69471-045c-11ed-8314-53a18710655a" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f69470-045c-11ed-8314-0f27cb57143c" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6bb83-045c-11ed-8314-c7d5c839dd02" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f69472-045c-11ed-8314-8d16bb73a3f9" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6bb85-045c-11ed-8314-c11575c9e5dd" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb84-045c-11ed-8314-f9255a013aff" class="se-fs- se-ff-   ">마무리!</span></p>
<p id="SE-02f6bb87-045c-11ed-8314-3392c35a05fd" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb86-045c-11ed-8314-5b80d448f1c8" class="se-fs- se-ff-   ">싸이 흠뻑쇼 2022 서울</span></p>
<p id="SE-02f6bb89-045c-11ed-8314-893390023ffd" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb88-045c-11ed-8314-3553ad82885d" class="se-fs- se-ff-   ">잠실 올림픽주경기장 콘서트!</span></p>
<p id="SE-02f6bb8b-045c-11ed-8314-6d79b19383da" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb8a-045c-11ed-8314-4d23ab8907fe" class="se-fs- se-ff-   ">너무 재밌고 신나게 즐기다 왔습니당</span></p>
<p id="SE-02f6bb8d-045c-11ed-8314-574906618696" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb8c-045c-11ed-8314-2bae30165ca1" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6bb8f-045c-11ed-8314-9be6f881d4a3" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb8e-045c-11ed-8314-219323958588" class="se-fs- se-ff-   ">싸이 콘서트는</span></p>
<p id="SE-02f6bb91-045c-11ed-8314-578a5cfc788f" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb90-045c-11ed-8314-817cb69d2859" class="se-fs- se-ff-   ">워낙 유명하고 주변사람들이</span></p>
<p id="SE-02f6bb93-045c-11ed-8314-a70e1f14fa4f" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb92-045c-11ed-8314-ad25884a2a41" class="se-fs- se-ff-   "> 추천도 많이 했었는데</span></p>
<p id="SE-02f6bb95-045c-11ed-8314-c360d2a1f828" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb94-045c-11ed-8314-bb81149c761a" class="se-fs- se-ff-   ">이렇게 다녀오게 되었네요!</span></p>
<p id="SE-02f6bb97-045c-11ed-8314-23e55209bb08" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb96-045c-11ed-8314-4971a09c9c0e" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6bb99-045c-11ed-8314-4bf657ac9b48" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6bb98-045c-11ed-8314-73cc193c3b49" class="se-fs- se-ff-   ">6시 42분 공연시작이었지만</span></p>
<p id="SE-02f6e2ab-045c-11ed-8314-255b3a890932" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2aa-045c-11ed-8314-6367814e2c00" class="se-fs- se-ff-   ">입장이 지연되어 6시45분에 시작했어요!</span></p>
<p id="SE-02f6e2ad-045c-11ed-8314-fd6137f3ebb3" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2ac-045c-11ed-8314-c7c10d0456c0" class="se-fs- se-ff-   ">정식공연은 9시에 끝났구!</span></p>
<p id="SE-02f6e2af-045c-11ed-8314-ef93535d5047" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2ae-045c-11ed-8314-197797566f01" class="se-fs- se-ff-   ">앵콜까지 10시30분에 끝났습니다!</span></p>
<p id="SE-02f6e2b1-045c-11ed-8314-63299e88f71f" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2b0-045c-11ed-8314-27c3320c6d9d" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6e2b3-045c-11ed-8314-59364967c58a" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2b2-045c-11ed-8314-2f7c9c15812f" class="se-fs- se-ff-   ">앵콜이 정말 끝나지 않더군요ㅋㅋ</span></p>
<p id="SE-02f6e2b5-045c-11ed-8314-212c077dadcc" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2b4-045c-11ed-8314-7df32c1a4504" class="se-fs- se-ff-   ">마블 쿠키영상 보는느낌?ㅋㅋ</span></p>
<p id="SE-02f6e2b7-045c-11ed-8314-2de9affe7b17" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2b6-045c-11ed-8314-113ced909fa5" class="se-fs- se-ff-   ">또있나? 설마 또하나? </span></p>
<p id="SE-02f6e2b9-045c-11ed-8314-c3270a9832bc" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2b8-045c-11ed-8314-e1f0dde71189" class="se-fs- se-ff-   ">정말 계속계속 앵콜에 답해주시니</span></p>
<p id="SE-02f6e2bb-045c-11ed-8314-7de34de05243" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2ba-045c-11ed-8314-af6648d1f169" class="se-fs- se-ff-   ">신기하고 좋았어요</span></p>
<p id="SE-02f6e2bd-045c-11ed-8314-cd5f0c6116a8" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2bc-045c-11ed-8314-67b27e6a9d7c" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6e2bf-045c-11ed-8314-2bd03f55943e" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2be-045c-11ed-8314-69eeab1a0787" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f6e2c1-045c-11ed-8314-d3abe52b49dc" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f6e2c0-045c-11ed-8314-1583bdf81c97" class="se-fs- se-ff-   ">​</span></p>
<p id="SE-02f709d3-045c-11ed-8314-b38e5f9c9426" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span id="SE-02f709d2-045c-11ed-8314-9fd31225bd1e" class="se-fs- se-ff-   ">보고느낀점?!</span></p>
<p class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"><span class="se-fs- se-ff-   ">내년에도 무조건 간다!!!!!!</span></p>
<div class="autosourcing-stub-extra" style="text-align: center;">
<p>&nbsp;</p>
</div>
<p id="SE-02f709d5-045c-11ed-8314-559f744b341c" class="se-text-paragraph se-text-paragraph-align-center " style="text-align: center;"></p>
<p style="text-align: center;">&nbsp;</p>', 1, '싸이 흠뻑쇼 2022 콘서트 서울 잠실 올림픽주경기장 신나게 놀다왔죠!', '2022-07-15', 1093200, 1);

insert into tourmates.trend (trend_id, created_date, last_modified_date, female, male, teenage, thirty, twenty, content_id)
values  (1, '2023-05-24 13:08:07.024000', '2023-05-24 13:08:07.050000', 10, 1, 0, 0, 1, 126498),
        (2, '2023-05-24 13:08:07.025000', '2023-05-24 13:08:07.050000', 0, 1, 0, 0, 1, 126498),
        (3, '2023-05-24 13:08:27.874000', '2023-05-24 13:08:29.416000', 0, 12, 0, 0, 12, 127797),
        (4, '2023-05-24 13:08:31.633000', '2023-05-24 13:08:31.644000', 0, 1, 20, 0, 1, 136639),
        (5, '2023-05-24 13:08:32.106000', '2023-05-24 13:08:32.117000', 10, 1, 0, 0, 1, 1121900),
        (6, '2023-05-24 13:08:32.450000', '2023-05-24 13:08:32.463000', 0, 1, 0, 0, 1, 1247247),
        (7, '2023-05-24 13:08:32.994000', '2023-05-24 13:08:33.003000', 0, 1, 0, 0, 1, 1020950),
        (8, '2023-05-24 13:08:41.337000', '2023-05-24 13:08:41.994000', 0, 5, 0, 0, 5, 127889),
        (9, '2023-05-24 13:08:54.053000', '2023-05-24 13:08:54.824000', 0, 7, 0, 0, 7, 129137),
        (10, '2023-05-24 13:09:08.449000', '2023-05-24 13:09:09.900000', 0, 6, 0, 30, 6, 128027);