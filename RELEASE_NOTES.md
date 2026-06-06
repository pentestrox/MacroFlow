# MacroWorkFlow - Release Notes

# 06-June-2026

## What's New in MacroFlow-v1.0.3
## Bug Fixes

### BUG-001 - Disconnected CustomNode intercepts live requests
## New Feature - Prompt User Capture (CustomNode)
Adds **Prompt** and **Prompt Label** columns to the *Downstream Captures* table on CustomNode.

**How it works:**
- When **Prompt** is checked for a capture entry, a dialog is shown to the user *before the workflow executes*, asking them to type in the value for that placeholder manually.
- The entered value is injected into the captures map and replaces `$placeholder$` tokens in all downstream nodes (RequestNode, other CustomNodes, code rules) exactly as a regex-captured value would.
- **Prompt Label** - optional custom label shown in the dialog (e.g. "Enter CSRF token"). If left blank, the placeholder name is used.
- **Regex is optional** when Prompt is checked - you can leave it blank.
- If Prompt is unchecked, the normal regex match-and-replace flow is completely unchanged.
- Previous run's value is pre-filled in the dialog as a default.
- **Skip** button closes the dialog without writing values; workflow continues with whatever seeded values already exist.

**Where it appears:** Downstream Captures tab on CustomNode only. RequestNode captures are not affected.

**Triggered from:** Play button (`runDirect`), intercept/trigger mode (`runTree`), and legacy JSON entry point (`runTrigger`).

---
# 02-June-2026

## What's New in MacroFlow-v1.0.2

UI Changes

---
# 01-June-2026

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
