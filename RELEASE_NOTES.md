# MacroWorkFlow - Release Notes

# 17-June-2026

## What's New in MacroFlow-v1.0.4 and Bug Fixes

### 1. ConditionalNode (IF/THEN/ELSE branching)

Route workflow execution based on request or response content.

- **AND / OR logic** - combine multiple match conditions with AND (all must match) or OR (any match triggers TRUE)
- **TRUE branch** (green port) and **FALSE branch** (orange port) - connect separate node chains for each outcome
- **Pre-check toggle** - when OFF the conditional evaluates the *original* live request/response without sending a separate pre-check request; the original hits the server exactly once
- **Re-fire on TRUE** - after the TRUE branch completes (e.g. refreshes a session token), the engine automatically re-fires the original request with the updated session - no extra Trigger Target node needed
- Conditions match against: Request Body, Response Body, Response Headers, Status Code
- Regex or plain-text matching per condition
- Full AND/OR short-circuit evaluation

### 2. Session Storage (SSN) - Global Placeholder Store

Persist extracted values across workflow steps and across multiple triggered requests.

- Define named placeholders: `$SSN_TokenName$` - available everywhere (request templates, custom rules, AI prompts)
- **SSN Manager** button (⊕ SSN in header) - view current values, add/rename/delete entries, see which capture last updated each key
- **Captures → SSN** - any capture entry can be wired to an SSN placeholder via the "SSN Target" column; value is written to the store the moment the capture fires
- **Prompt → SSN** - Prompt User Capture nodes can write their entered value directly to SSN
- **Safe delete** - deleting an SSN key warns if it has a wired capture source; on confirm, scrubs `ssnTarget` from all nodes in all workflows
- **Import clean-replace** - importing a JSON workflow replaces SSN definitions cleanly (no stale merge)
- Persisted to Burp extension settings (`macroflow_ssn_defs`); survives Burp restart

### 3. Two New Themes

| Theme | Description |
|-------|-------------|
| **Silver White** | Clean light grey palette, glossy card style |
| **Grey+Red** | Flat dark grey cards with red border accents |

Switch theme: right-click canvas → ⚙ General Settings → theme cycle button  
Or: **⚙** header button → User Preferences

### 4. Two New UI Templates

| Template | Description |
|----------|-------------|
| **Pipeline Card** | Workflow rows with a structured card layout |
| **Radial** | Hover any workflow row to reveal a fan of action buttons (bloom menu) |

Change template: **⚙** → User Preferences → UI Template


---
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
