## MVP
: Model - View - Presenter            
![image](https://user-images.githubusercontent.com/55985789/92193243-ca0a3280-eea2-11ea-84e8-4301b2d48e77.png)         

Model : Data Access layer, Use Case      
View : Presentation Layer         
Presenter : Business Logic Layer


## MVP는 MVC를 개선한 모델.
: MVC패턴은 보다시피 뷰와 모델의 서로 간 의존도가 높다.              
-> 그래서 뷰와 모델의 의존도를 낮추기위해 Presenter를 넣어 의존도를 낮춰 개선한 모델이 MVP이다.             
-> view/model은 서로 알 필요가 없고 presenter만 알면 된다.

## 왜 MVC에서 컨트롤러가 문제가 되었는가?
* 테스트 용이성 - 컨트롤러가 안드로이드 API에 깊게 종속되므로 유닛 테스트가 어렵다.
* 모듈화 및 유연성 - 컨트롤러가 뷰에 결합되며, 뷰의 확장일 수도 있다. 뷰를 변경하면 컨트롤러로 돌아가서 변경해야 한다.
* 유지 보수 - 시간이 지남에 따라 보다 많은 코드가 컨트롤러로 모이면서 비대해지고 문제가 발생하기 쉬워진다.
-> 결국 위의 사항들을 개선할 수 있는 모델로 MVP가 나왔다.

## Test 플로우 정리. 
1. View로 사용자의 입력이 들어옴
2. View는 Presenter에 작업을 요청 함
3. Presenter에서 필요한 데이터를 Model에 요청
4. Model은 Presenter에 필요한 데이터를 응답
5. Presenter는 View에 데이터를 응답 함
6. View는 Presenter로부터 받은 데이터로 화면에 보여주게 됨


## 단점. 
View와 Model의 의존성을 낮춘대신 View와 Presenter가 1:1로 강한 의존성을 가지게 된다.                    
-> 이를 개선할 수 있는 방법에는 MVVM패턴 등이 있다.

## 핵심. 
항상 presenter를 통해 동작하는 것. 뷰와 모델을 알 필요 없으니 의존도를 낮출 수 있다.


## Reference.              
[MVP,MVC,MVVM 비교](http://itnovice1.blogspot.com/2019/01/mvc-mvp-mvvm.html?m=1)               
[MVC vs. MVP vs. MVVM](https://academy.realm.io/kr/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/)
