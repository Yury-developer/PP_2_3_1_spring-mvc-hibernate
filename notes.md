

В **Idea** прописал в параметры **jvm**: '`-Dfile.encoding=UTF8`' 
Без этого вообще в консоль не писало русскоязычные символы

---

* **HTTP** (Hypertext Transfer Protocol) виды запросов, каждый из которых выполняет определенное действие на веб-сервере. Вот основные виды HTTP запросов:

* **GET**: Запрос на получение данных с сервера. Обычно используется для запроса ресурсов (например, HTML страниц, изображений, стилей и скриптов) с сервера. GET запросы могут быть кэшированы и добавлены в историю браузера, поэтому они не должны содержать чувствительных данных в параметрах URL.

* **POST**: Запрос на отправку данных на сервер для обработки. POST запросы используются, когда необходимо отправить данные формы или другие данные на сервер. Эти запросы могут содержать более объемные данные и чувствительную информацию, такую как пароли или личные данные.

* **PUT**: Запрос на обновление существующих данных на сервере. PUT запросы используются для замены существующего ресурса на сервере новым содержимым. Они могут быть использованы для обновления данных, создания новых ресурсов или замены существующих.

* **DELETE**: Запрос на удаление ресурса с сервера. DELETE запросы используются для удаления существующих ресурсов с сервера. Они могут быть использованы для удаления файлов, записей в базе данных или любых других ресурсов, которые больше не нужны.

* **PATCH**: Запрос на частичное обновление ресурса. PATCH запросы используются для обновления части ресурса на сервере, оставляя остальную часть неизменной. Это позволяет экономить пропускную способность сети и уменьшать время обработки запроса.

* **HEAD**: Запрос на получение заголовков ответа без тела ответа. HEAD запросы используются для получения информации о ресурсе, такой как его тип, дата последнего изменения и размер, без загрузки всего содержимого. Это может быть полезно для проверки доступности ресурса или для получения метаданных без необходимости загружать всю информацию.

---

Хороший **видео-урок**, помог мне в решении задачи ([ссылка](https://youtu.be/JaVGIYxE23c?si=QwAjHxxFQ7iMUKz6)), ~~все на пальцах разжевано~~..

_По данному уроку:_ Репозиторий стартового проекта ([ссылка](https://github.com/NeilAlishev/SpringCourse/tree/master/Lesson23_Starter.CRUD_App3))

_По данному уроку:_ Репозиторий урока ([ссылка](https://github.com/NeilAlishev/SpringCourse/tree/master/Lesson23.CRUD_App3))

---


setTemplateMode(TemplateMode.HTML): Этот метод устанавливает режим шаблона. В данном случае мы устанавливаем режим HTML, что означает, что Thymeleaf будет интерпретировать наши шаблоны как HTML-шаблоны. Это важно для правильной обработки и отображения HTML-кода в шаблонах.

setCacheable(false): Этот метод указывает, должен ли Thymeleaf кешировать шаблоны или нет. В данном случае мы устанавливаем значение false, что означает, что кеширование отключено. Это полезно во время разработки, так как при изменении шаблонов нам не нужно будет перезапускать приложение, чтобы увидеть изменения. Однако в производственной среде обычно рекомендуется включить кеширование для улучшения производительности.

---

**@Fetch** - это аннотация из **Hibernate**, которая позволяет настроить **способ извлечения связанных сущностей из базы данных**. Она позволяет управлять стратегией извлечения данных при обращении к коллекциям или ассоциациям.

В Hibernate существует несколько стратегий извлечения данных (FetchMode), которые можно использовать с аннотацией **@Fetch**:

FetchMode.**JOIN**: Эта стратегия используется по умолчанию для связей `@ManyToOne` и `@OneToOne`. Hibernate пытается выполнить запрос с использованием **JOIN**, чтобы извлечь все связанные данные одним запросом. Например:
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id")
private Department department;
```

FetchMode.**SELECT**: Эта стратегия также используется по умолчанию, но она используется для ленивой загрузки (FetchType.LAZY). Hibernate будет выполнять дополнительный запрос, когда потребуется доступ к связанным данным. Например:
```java
@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
private List<Employee> employees;
```

FetchMode.**SUBSELECT**: Эта стратегия позволяет выполнить один дополнительный запрос для загрузки всех связанных данных в отдельном запросе. Это может быть полезно, когда у вас есть несколько коллекций, которые должны быть загружены отдельно, чтобы избежать проблем с MultipleBagFetchException. Например:
```java
@OneToMany(mappedBy = "user")
@Fetch(FetchMode.SUBSELECT)
private List<PhoneEntry> phones;
```

FetchMode.**LAZY**: Эта стратегия указывает, что связанные данные должны быть загружены лениво, только когда они действительно нужны. Например:
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id")
private Department department;
```

Пример использования **@Fetch**:

```java
@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    private List<PhoneEntry> phones;

    // геттеры и сеттеры
}
```

Этот пример показывает, что при загрузке пользователя также будут загружены все его телефоны одним запросом.

---
