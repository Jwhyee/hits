# Kotlin GitHub Hits Counter 🧮

A lightweight service to track and display view counts on GitHub README files using customizable SVG badges.  
Built with Kotlin and Spring Boot.

---

## 📸 Example

You can use the following markdown to embed a badge:

```markdown
![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/Jwhyee&color=4caf50&r=111)
```

It will render like this:

![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/Jwhyee&color=4caf50&icon=zap&r=111)

👉 You can also generate a badge interactively at:  
[https://hit-s.kro.kr/count/generate](https://hit-s.kro.kr/count/generate)

---

## 🚀 Features

- Increments view count for a specified `url`
- Dynamically generates SVG badge with:
    - Custom `color` (right label background)
- Lightweight and fast: In-memory storage using `ConcurrentHashMap`

---

## ⚙️ Usage

### Endpoint

```
GET /api/count/badge?url={URL}&title={TITLE}&color={COLOR}
```

### Example

```
GET /api/count/badge?url=https://github.com/your/repo&title=Views&color=green
```

| Parameter | Description                            |
|-----------|----------------------------------------|
| `url`     | Target URL to track view count         |
| `color`   | Right-side badge background color      |

---

## 🧱 Tech Stack

- **Kotlin** – Concise and expressive JVM language
- **Spring Boot** – Robust framework for RESTful APIs
- **ConcurrentHashMap** – Fast in-memory repository
- **SVG Generator** – Custom-built dynamic badge renderer

---

## 🧠 Inspiration

This project was inspired by [`gjbae1212/hit-counter`](https://github.com/gjbae1212/hit-counter) – MIT License

The implementation here is completely rewritten using Kotlin and Spring Boot.

---

## 📄 License

MIT License  
© 2025 Jwhyee

See the [LICENSE](./LICENSE) file for full details.
