# VelocityFakeOnline (Русская версия)

[![Velocity](https://img.shields.io/badge/Velocity-3.1.1+-blue)](https://velocitypowered.com/)
[![License](https://img.shields.io/badge/License-GPLv3--impersonation--restriction-orange)](LICENSE)

Плагин для Velocity, который динамически подменяет онлайн на сервере.  
Число игроков в списке серверов постоянно немного меняется – то растёт, то падает, имитируя активный заход и выход реальных людей.

---

## ✨ Возможности

- 🎭 Фейковый онлайн, плавающий в реальном времени (каждые несколько секунд ±1)
- 🔧 Настраиваемые границы колебаний, базовое значение и интервал обновления
- 🧩 Подмена максимального количества слотов (max players)
- ⚡ Не влияет на производительность прокси
- 📦 Простая настройка через `config.yml`

---

## 📁 Конфигурация

После первого запуска плагин создаст `plugins/fakeonline/config.yml`.

fakeOnline:
  count: 7               # Среднее значение фейкового онлайна
  slots: 20              # Показываемое количество слотов (0 – не менять)
  min-fake: 3            # Минимальный фейк
  max-fake: 12           # Максимальный фейк
  update-interval: 5     # Интервал изменения (в секундах)
После изменений перезагрузите плагин или перезапустите прокси.

🚀 Установка
Скачай VelocityFakeOnline.jar со страницы Releases

Помести JAR в папку plugins/ твоего Velocity

Запусти или перезапусти прокси

(По желанию) настрой config.yml под себя

Требования: Velocity 3.1.1 и выше

💡 Пример из игры
Вместо статичного числа 12/100 ты увидишь:

text
ТвойСервер  19/20
ТвойСервер  20/20
ТвойСервер  18/20
...
Число меняется каждые несколько секунд, имитируя реальных игроков.

📄 Лицензия
Данный проект распространяется под лицензией GNU GPLv3 с дополнительным запретом на имперсонацию автора (Saturn).
Подробнее см. LICENSE.

VelocityFakeOnline (English Version)
https://img.shields.io/badge/Velocity-3.1.1+-blue
https://img.shields.io/badge/License-GPLv3--impersonation--restriction-orange

A Velocity proxy plugin that dynamically fakes the online player count.
The displayed player number fluctuates in real time, creating the illusion of active player traffic on your server list.

✨ Features
🎭 Fake player count that changes over time (up/down by 1 every few seconds)

🔧 Configurable fluctuation range, base count, and update interval

🧩 Also replaces the maximum player slots shown in the server list

⚡ Lightweight and fully asynchronous – no impact on proxy performance

📦 Simple config – just drop the JAR and edit config.yml

📁 Configuration
After the first start, a config.yml is created in plugins/fakeonline/.

yaml
fakeOnline:
  count: 7               # Average fake online
  slots: 20              # Fake max slots (0 = keep original)
  min-fake: 3            # Minimum fake players
  max-fake: 12           # Maximum fake players
  update-interval: 5     # Seconds between fluctuations
After editing, reload the plugin or restart the proxy.

🚀 Installation
Download the latest VelocityFakeOnline.jar from Releases

Place it in the plugins/ folder of your Velocity proxy

Start or restart the proxy

(Optional) Edit plugins/fakeonline/config.yml to fine-tune the behaviour

Requirements: Velocity 3.1.1 and above

💡 How it looks
Instead of a static number like 12/100, you'll see:

text
YourServer  19/20
YourServer  20/20
YourServer  18/20
...
The number changes every few seconds, simulating real players joining and leaving.

📄 License
This project is distributed under the GNU GPLv3 license with an additional restriction prohibiting impersonation of the author (Saturn).
See LICENSE for details.
