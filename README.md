# Kotlin GitHub Hits Counter 🧮

A simple hit counter for GitHub READMEs.  
Built with Spring Boot and Kotlin, this service increases the view count based on a given URL and returns a **badge in SVG format**.

## 📸 Example

```markdown
![Hits](https://your-domain.com/api/count/incr/badge.svg?url=https://github.com/your/repo)
```

<!-- ![Hits](https://your-domain.com/api/count/incr/badge.svg?url=https://github.com/your/repo) -->

---

## 🚀 Features

- Increments view count based on the specified `url`
- Dynamically generates SVG badge (`title`, `color` customizable)
- Built with Kotlin and Spring Boot
- In-memory storage (easily extendable to Redis or database)

---

## ⚙️ Usage

### Endpoint

```
GET /api/count/incr/badge.svg?url={URL}&title={TITLE}&color={COLOR}
```

### Example

```
GET /api/count/incr/badge.svg?url=https://github.com/your/repo&title=Views&color=green
```

| Parameter | Description                     | Default |
|-----------|----------------------------------|---------|
| `url`     | Target URL to track view count  | Required |
| `title`   | Left-side badge label           | `Hits`  |
| `color`   | Right-side badge color          | `blue`  |

---

## 🧱 Tech Stack

- Kotlin
- Spring Boot (Web)
- In-memory repository using ConcurrentHashMap
- Simple text-based SVG generator

---

## 🧠 Inspiration

This project was inspired by the following open-source project:

- [`gjbae1212/hit-counter`](https://github.com/gjbae1212/hit-counter) – MIT License

The implementation here is completely rewritten using Kotlin and Spring Boot.

---

## 📄 License

```
MIT License  
Copyright (c) 2025 Jwhyee
```

For more details, see the [`LICENSE`](./LICENSE) file.
