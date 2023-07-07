Чек-лист FE_8_online+FS_2_online(2)
1.     Посилання на гіт проекту та презентацію:
https://github.com/bondoleks/twitter-clone-application
лінк презентація
2.     Посилання на задеплоїний сайт – https://twitter-clone-application.vercel.app
3.     Група – FE_8_online+FS_2_online(2)
4.     К-ть студентів та ПІБ і хто яку частину робив:
BackEnd:
1.     Бондарчук О.В. :
Spring security:
Configuration
DTO
UserImpl
UserImplInterface
JWT:
Configurer
Filter
Exception
Provider
User
UserFactory
Properties
	      MailSender:
Configuration
Text messages
WebSockets:
Configuration to work
Endpoints:
Notifications
Registration
Authentification
Admin
Deploy project:
Heroku
Vercel
Oauth2 with Google:
Handler
Service
User
Properties
Connect DB on removed server:
Heroku-PostgreSQL
Create and update repository
Scrum master BackEnd group

2.     Семенко Сергій:
Mapper:
Configuration
Claudinary:
Configuration
створення базового методу для збереження зображень для застосування на всіх етапах програмування
збереження зображень для TWEET/REPLY/QUOTE з можливістю розподілу по фолдерах у форматі …/userId_{userId}/TWEET_{tweetId}_photo_{number}
Home page:
пошук юзерів по співпадінню firstname та/або lastname або username з пошуковим запитом
пошук твітів по наявності набору слів з пошукового запиту в тексті твітів
Tweets endpoints:
збереження нових TWEET/REPLY/QUOTE (далі - TRQ)
обробка та збереження даних для здійснення пошуку твітів по кобінаціі слів
формування DTO для відображення TRQ, а саме
текст та зображення (при наявності)
кількісні параметри - like, retweet, quote, bookmark, view
параметри юзера - автора TRQ
належність TRQ до гілки, та, як що “так” - формування всієї гілки, або частини в залежності від потреб FrontEnd
відпрацювання дій щодо TRQ - like(+/-), retweet(+/-), bookmark(+/-)
формування стрічки з твітів за вказаними параметрами, а також коментів до обраного твіта з використанням класу Page та інтерфейсу PagingAndSortingRepository
обробка та збереження даних для підрахунку кількості показів твітів 
Messages endpoints:
пошук юзерів по співпадінню firstname та/або lastname або username з пошуковим запитом для створення чата
створення та збереження чата з обраним юзером
формування та збереження чат-листа з відображенням останніх повідомлень в існуючих чатах (при їх наявності)
додавання та видалення обраного чата з чат-листа
створення та збереження повідомлень у чаті
формування стрічки всіх повідомлень чата з використанням класу Page та інтерфейсу PagingAndSortingRepository
Bookmark endpoints:
формування стрічки твітів юзера, збережених у закладках з використанням класу Page та інтерфейсу PagingAndSortingRepository

3. Сизько Олександра
Models: 
Base Entity
Auditable
Notification
Tweet
User
Chat
TweetAction
Message
User endpoints:
Створення відповідних репозиторію, логіки в service, фасаді, контролері, dto та методів, 
Get User Profile
Get Following/Followers
Follow/Unfollow
Suggestions to follow
User Update
Make dto from entity
Make entity from dto
Save User



FrontEnd:
4.     Бехтір Олена:
розгортання front-end частини проекту в середовищі vite.js
Sidebar & Private routing:
верстка десктопної, планшетної та мобільної версій
верстка випадаючого дроп-меню на мобільній версії
створення роутингу та налаштування приватного роутингу
прив’язка сайдбару до роутингу (sidebar, connect with routing)
Themes (Create default MUI theme with customizing modal):
створення трьох тем сайту (світлої - Default, темної - Dim та чорної - Lights Out)
верстка модального вікна для зміни тем та налаштування можливості зміни теми та кольору кнопок
підключення зміни тем до всіх сторінок та низки модальних вікон (вікно, яке відповідає за зміну тем, випадаюче дроп-меню на мобільній версії, модальне вікно для створення та відправки твіта, модальне вікно для корегування профілю юзера з додатковими модалками до нього)
Tweet form (with modal):
верстка модального вікна для створення та відправки твіта 
верстка мобільної версії твіт-форми
Connect tweet form with create tweet endpoint:
налаштування створення та відправки твіта (з картинками та без)
Profile page (with update user modal):
верстка сторінки профілю юзера
верстка модального вікна EditProfileUser та додаткових модальних вікон для зміни BirthDate
сonnect Profile Page & Modal Edit User with create user endpoint



    5.Єрмоленко Віталій:
Підключення Redux та Redux-Thunk до проекту
Створення store та основних reducers
	     Main page: 
Верстка сторінки та розміщення на ній tweets
Налаштування infinity Scroll
створення операцій через Redux для поєднання з ендпоінтами
     Home page:
Верстка сторінки та розміщення на ній tweets
Налаштування infinity Scroll
створення операцій через Redux для поєднання з ендпоінтами
	     Login and Registration:
оформлення модальних вікон основних та допоміжних(сповіщення користувача)
валідація за допомогою formik та yap
логіка надсилання та обробки запитів щодо реєстрації та  входу, і обробка та вивід помилок   
	     Tweet page:
Верстка сторінки та розміщення на ній твіту чи його гілки, та виведення коментарів до твіту
Налаштування infinity Scroll
створення операцій через Redux для поєднання з ендпоінтами
верстка та логіка реакцій на твіт
     Tweet:
верстка вигляду твіта, розмішення в ньому елементів, панелі реакцій
логіка реакцій та створення та обробка запитів
модальні вікна для відображення зображень на весь екран та розширених функцій 
фіксування перегляду твіта та надсилання запиту по цій події           
	
     Reply:
налаштування створення та відправки reply (з картинками та без)
верстка адаптивного модально вікна для reply
верстка відображення reply
створення операцій через Redux для поєднання з ендпоінтами
     Quote retweet:
налаштування створення та відправки quote retweet (з картинками та без)
верстка адаптивного модально вікна для quote retweet 
створення операцій через Redux для поєднання з ендпоінтами
	    Bookmark Page:
Верстка сторінки та розміщення на ній tweets
Налаштування infinity Scroll
створення операцій через Redux для поєднання з ендпоінтами
              Notification page


    6.Олексій Стребков:
General layout of the application
	   Search
Search in the right column with logic to search users
Layout and logic for the modal that accompanies the search process
	   Messages Page
Layout of the page
Inner search for users to create new chat
Inner search for user messages in chat
Modals for search the users and messages search
Adjustment of the routes for this page
Management of the correct rendering of the page elements since this is the only page that change the middle column
Logic for the chat itself to arrange the chat between the users
Websocket logic for the chats from front end side
	

7.Пилипенко Артем
Message endpoints
ExceptionHandler
8.Сшулін Роман:
 

5.     Скільки часу зайняв проект – 2 місяці
6.     Тема проекту – Розробка соціальної мережі
7.     Які були поставлені завдання: Розробити повноцінну соціальну мережу максимально схожу, по базовому функціоналу, на соціальну мережу Твітер
8.     Як ментор допомагав під час проекту: 
·       Розробляв стратегію розробки
·       Пояснював як зв’язувати бек та фронт енд
·       Ставив задачі та назначав їх виконавців
·       Допомогав при складних задачах (направляв в правильний «бік»)
9.     Які технології були використані:
·       Основні інструменти: Backend (Java, Spring, Maven, Postgresql), Frontend (React, MUI, Redux, Routing)
·       Додаткові інструменти: Heroku, Vercel, Google settings
·       Бібліотеки:
  Backend: 
  Spring-boot-starter-web
  Spring-boot-starter
  Thymeleaf
  Lombok
  JPA
  h2database
  Postgresql 
 Security
  Javax
  Modelmapper
  Jakarta
  Jsonwebtoken
  Websocket
  Validation
  MailSender
  Cloudinary
  Oauth2
  Plugins

Front-end:
 Formik
npm
yap
vibility-sensor,
axios
·       Тестування: PostMan
10.  Які були етапи роботи:
·       Знайомство з командою
·       Вибір направлення, теми проекту
·       Аналіз того, що нам потрібно
·       Постановка тасків
·       Розподілення тасків
·       Робота над тасками
·       Комунікація між членами команди
·       Доопрацювання та вдосконалення проекту
·       Готовий проект
