# Kotlin GitHub Hits Counter 🧮

A lightweight service to track and display view counts on GitHub README files using customizable SVG badges.  
Built with Kotlin and Spring Boot.

---

## 📸 Example

### 🔹 v1 – Simple Style

```markdown
![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/Jwhyee&icon=zap&color=4caf50&r=v1)
```

![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/Jwhyee&icon=zap&color=4caf50&r=v1)

---

### 🔸 v2 – Visual Style

```markdown
[![Hits](https://hit-s.kro.kr/api/v2/count/badge?url=https://github.com/Jwhyee&title=Jwhyee&color=4CAF50&icon=zap&r=v2)](https://hit-s.kro.kr/count/generate)
```

[![Hits](https://hit-s.kro.kr/api/v2/count/badge?url=https://github.com/Jwhyee&title=*✧⁺˚⁺+⁽⁽ଘ(｡•ө•｡)ଓ⁾⁾+ु+｡.:･゜*✧&color=f781ff&icon=heart&r=v2)](https://hit-s.kro.kr/count/generate)

👉 You can also generate a badge interactively at [Generating page](https://hit-s.kro.kr/count/generate)

---

## 🧭 Badge Versions

### 🔹 v1 – Simple Style

- Classic-style SVG badge
- Includes:
  - Title (the last path segment of the URL)
  - Total view count
  - Custom icon (emoji-style SVG)
  - Custom right-side background color
- Example:
  ```markdown
  ![Hits](https://hit-s.kro.kr/api/count/badge?url=https://github.com/your/repo&icon=zap&color=4caf50&r=v1)
  ```

### 🔸 v2 – Visual Style

- Rich visual badge inspired by platforms like solved.ac
- Includes:
  - Custom title (optional; if omitted, the last path segment of the URL will be used)
  - View counts over the past 3 days (today, 1 day ago, 2 days ago)
  - Simple bar chart visualization
  - Overall ranking among all badge users
- Best used for **personal profile highlights**
- Example:
  ```markdown
  [![Hits](https://hit-s.kro.kr/api/v2/count/badge?url=https://github.com/Jwhyee&title=Jwhyee&color=4CAF50&icon=zap&r=v2)](https://hit-s.kro.kr/count/generate)
  ```

---

## 🚀 Features

- Increments view count for a specified `url`
- Dynamically generates SVG badge with:
  - Custom color (v1, v2)
  - Custom icon (v2)
  - Multi-day history (v2)
- Lightweight and fast: In-memory storage using `ConcurrentHashMap`

---

## ⚙️ Usage

### Endpoint

```
GET /api/count/badge?url={URL}&color={COLOR}&icon={ICON}&r={VERSION}
```

### Example

```
GET /api/count/badge?url=https://github.com/your/repo&icon=fire&color=ff9800&r=v1
```

| Parameter  | Description                                           |
|------------|-------------------------------------------------------|
| `url`      | Target URL to track view count                        |
| `title`    | (v2 only, optional) Custom badge title                |
| `color`    | Right-side badge background color (hex or name)       |
| `icon`     | Emoji-style SVG icon (e.g., zap, fire, heart, star)   |
| `r`        | Badge version: `v1` (simple), `v2` (visual)           |

---

## 🧱 Tech Stack

- **Kotlin** – Concise and expressive JVM language
- **Spring Boot** – Robust framework for RESTful APIs
- **ConcurrentHashMap** – Fast in-memory repository
- **SVG Generator** – Custom-built dynamic badge renderer

---

## 🧠 Inspiration

This project was inspired by [`gjbae1212/hit-counter`](https://github.com/gjbae1212/hit-counter) – GPL-3.0 License
The implementation here is completely rewritten using Kotlin and Spring Boot.

---

## 📄 License

This project is based on gjbae1212's hit-counter repository (GPL-3.0).
The following major changes have been made.

- Project structure fully rewritten using Kotlin + Spring Boot
- Hit count storage switched from static to dynamic memory
- New SVG V2 design with animation
- In-memory and Redis support added
- GitHub Actions for deployment

Full source code is available under the same GPL v3.0 license.
© 2025 Jwhyee

See the [LICENSE](./LICENSE) file for full details.
