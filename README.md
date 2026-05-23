# VelocityFakeOnline RU

Плагин для Velocity, который динамически подменяет онлайн на сервере.
Число игроков в списке серверов постоянно немного меняется – то растёт, то падает, имитируя активный заход и выход реальных людей.

✨ Возможности
🎭 Фейковый онлайн, плавающий в реальном времени (каждые несколько секунд ±1)

🔧 Настраиваемые границы колебаний, базовое значение и интервал обновления

🧩 Подмена максимального количества слотов (max players)

⚡ Не влияет на производительность прокси

📦 Простая настройка через config.yml

📁 Конфигурация
После первого запуска плагин создаст plugins/fakeonline/config.yml:

yaml
fakeOnline:
  count: 7               # Среднее значение фейкового онлайна
  slots: 20              # Показываемое количество слотов (0 – не менять)
  min-fake: 3            # Минимальный фейк
  max-fake: 12           # Максимальный фейк
  update-interval: 5     # Интервал изменения (в секундах)
🚀 Установка
Скачай VelocityFakeOnline.jar со страницы Releases

Помести JAR в папку plugins/ твоего Velocity

Запусти или перезапусти прокси

(По желанию) настрой config.yml под себя


# VelocityFakeOnline EN

A Velocity proxy plugin that dynamically fakes the online player count.  
The displayed player number fluctuates in real time, creating the illusion of active player traffic on your server list.

---

## ✨ Features

- 🎭 Fake player count that changes over time (up/down by 1 every few seconds)
- 🔧 Configurable fluctuation range, base count, and update interval
- 🧩 Also replaces the maximum player slots shown in the server list
- ⚡ Lightweight and fully asynchronous – no impact on proxy performance
- 📦 Simple config – just drop the JAR and edit `config.yml`

---

## 📸 How it looks

In your server list, instead of a static number like `12/100`, you'll see something like:
MyServer 19/20
MyServer 20/20
MyServer 18/20
...

text

The number changes every few seconds, simulating real players joining and leaving.

---

## 📁 Configuration

After the first start, a `config.yml` is created in `plugins/fakeonline/`.

fakeOnline:
  count: 7              # Average fake online
  slots: 20             # Fake max slots (0 = keep original)
  min-fake: 3           # Minimum fake players
  max-fake: 12          # Maximum fake players
  update-interval: 5    # Seconds between fluctuations
You can adjust the values and reload the plugin (or restart the proxy) to apply changes.

🚀 Installation
Download the latest VelocityFakeOnline.jar from Releases

Place it in the plugins/ folder of your Velocity proxy

Start or restart the proxy

(Optional) Edit plugins/fakeonline/config.yml to fine-tune the behaviour
