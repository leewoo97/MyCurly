# MyCurly
바쁜 직장인을 위한 간편 재료 주문 MyCurly

# Why make it?
아침에 출근할때 버스, 지하철에서 먹고싶은 음식을 검색해보세요!</br>
음식에 필요한 재료가 자동으로 장바구니에 담기고, 퇴근 후 집에 왔을때는 재료가 도착해있을거에요!

## 기술스택
<p>
  <img src="https://img.shields.io/badge/-SpringBoot-blue"/>&nbsp
  <img src="https://img.shields.io/badge/-JPA-red"/>&nbsp
  <img src="https://img.shields.io/badge/-H2-violet"/>&nbsp
  <img src="https://img.shields.io/badge/-MySQL-yellow"/>&nbsp
  <img src="https://img.shields.io/badge/-SpringBatch-green"/>&nbsp
</p>

## ERD
![image](https://github.com/user-attachments/assets/050e4b0b-becf-4920-8203-e64438c1b6a8)

# 개발일지
## 2024-11-04
#### • ERD 작성 완료
#### • Entity 연관관계 매핑 완료
#### • Job -> 크롤링Step -> UpdateInventoryStep 순으로 작업 배치</br>
   크롤링 Step : 작업이 끝나면 크롤링한 데이터를 가진 csv파일 생성</br>
   UpdateInventoryStep : csv파일을 분석하여, Inventory엔티티에 저장</br>
   ※<a href="https://dldnwls009.tistory.com/24">[SpringBatch]ItemReader 구현체 종류</a>

## 2024-11-05
#### • 크롤링Step에서 저장된 csv파일을 UpdateInventoryStep의 ItemReader에서 못읽는 오류 해결 
#### • application-dev파일로 운영서버와 개발서버의 csv파일 저장경로를 유동적으로 바꿀수있게 적용

## 2024-11-07
#### • GCP VM인스턴스 생성
#### • 인스턴스 환경에서도 크롤링 수행되도록 SeleninumCrawler Headless모드로 변경 -> 서버에서 크롤링 성공
※<a href="https://dldnwls009.tistory.com/31">인스턴스에서 크롤링을 해보자(Selenium 4.6버전 이상)</a>
#### • 서버 22번포트 열어서 로컬에서 scp로 application-prod.properties파일 전송 완료
#### • 서버에서도 크롤링후 DB에 csv파일 분석해서 저장하는 SpringBatch작업 제대로 작동함

## Trouble Shooting
### 1. 크롤링 도중 WebElement로 인한 StaleElementReferenceException
#### 해결 방법 : <a href="https://dldnwls009.tistory.com/27">WebElement 객체를 아시나요?</a>

### 2. ItemReader가 csv파일을 못 읽는 상황 발생
#### 해결 방법 : <a href="https://dldnwls009.tistory.com/28">@StepScope(LateBinding)에 대해서 아시나요?</a>

### 3. 개발서버와 운영서버의 csv파일 저장경로가 다른데 어떻게 설정해야하지?
#### 해결 방법 : <a href="https://dldnwls009.tistory.com/29">application-dev.properties와 @Value</a> 
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://dldnwls009.tistory.com/30">properties를 개발서버와 운영서버에서 환경변수로 적용해보자</a>
