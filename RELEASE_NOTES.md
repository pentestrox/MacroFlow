# MacroWorkFlow - Release Notes

## What's New in MacroWorkFlow-v1.0.1

### Search Box in Node View & Node Edit Window
A search box has been added to both the Node View and Node Edit windows, making it easier to navigate and filter captures, placeholders, and fields when working with large or complex workflows.

### Add Capture from Selection (Right-Click Context Menu)
Right-clicking inside the response area now includes an **"Add Capture from Selection"** option. Selecting it opens a popup where you can:
- Define your own capture pattern
- See a **live match count** for your regex against the current HTTP response data

This makes it faster to build precise captures without leaving the editor.

### Capture View Checkbox in Node Display
**IgniteNode** and **CustomNode** now include a **Capture View** checkbox directly on the node card, letting you toggle the captures panel open or closed without opening the editor.

---

## Bug Fixes

- **Regex Match & Replace for Response Header + Body** - Fixed an issue where regex match and replace rules were not being applied correctly to response headers and body content.
