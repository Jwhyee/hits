# Kotlin GitHub Hits Counter 🧮

A lightweight service to track and display view counts on GitHub README files using customizable SVG badges.  
Built with Kotlin and Spring Boot.

---

## 📸 Example

You can use the following markdown to embed a badge:

```markdown
![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/Jwhyee&color=4caf50&icon=zap)
```

It will render like this:

![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/Jwhyee&color=4caf50&icon=zap)

👉 You can also generate a badge interactively at:  
[https://hit-s.kro.kr/count/generate](https://hit-s.kro.kr/count/generate)

---

## 🚀 Features

- Increments view count for a specified `url`
- Dynamically generates SVG badge with:
    - Custom `title` (left label)
    - Custom `color` (right label background)
    - Custom `icon` (left-side emoji)
- Lightweight and fast: In-memory storage using `ConcurrentHashMap`
- Easily extendable to Redis or a relational database

---

## ⚙️ Usage

### Endpoint

```
GET /api/count/badge?url={URL}&title={TITLE}&color={COLOR}&icon={ICON}
```

### Example

```
GET /api/count/badge?url=https://github.com/your/repo&title=Views&color=green&icon=zap
```

| Parameter | Description                            | Default |
|-----------|----------------------------------------|---------|
| `url`     | Target URL to track view count         | *Required* |
| `title`   | Left-side badge label                  | `Hits`  |
| `color`   | Right-side badge background color      | `blue`  |
| `icon`    | Left-side emoji icon (e.g. `zap`, `fire`, `star`) | `zap` |

---

## 🧱 Tech Stack

- **Kotlin** – Concise and expressive JVM language
- **Spring Boot** – Robust framework for RESTful APIs
- **ConcurrentHashMap** – Fast in-memory repository
- **SVG Generator** – Custom-built dynamic badge renderer

---

## 🧠 Inspiration

This project was inspired by:

- [`gjbae1212/hit-counter`](https://github.com/gjbae1212/hit-counter) – MIT License

The implementation here is completely rewritten using Kotlin and Spring Boot.

---

## 📄 License

MIT License  
© 2025 Jwhyee

See the [LICENSE](./LICENSE) file for full details.