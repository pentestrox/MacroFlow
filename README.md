# MacroFlow - Burp Suite Extension (Version 1.0.0)

> ⚠️ **Development Phase (Solo - Fun Project)**

---

## What is MacroFlow?

MacroFlow is a visual, node-based macro workflow engine for Burp Suite.
It replaces Burp's built-in Macro system with a drag-and-drop canvas where you
build multi-step HTTP flows, automatically inject session tokens, and validate
reflected payloads across multiple (MAX 3) targets - all without writing a single script.

<img width="1309" height="722" alt="image" src="https://github.com/user-attachments/assets/bd18727f-796b-43c4-a80a-b230a3667722" />



## What it does

Macro Builder adds a **"Macro Builder"** tab to Burp Suite. Inside it you get a canvas where you connect nodes into workflows. Each workflow describes a chain of HTTP actions that fires automatically when Burp tools (Repeater, Intruder, Proxy, Scanner, other extensions) send a matching request.

---
## Demo

**1. Send to Macro Workspace**

Right-click any request → MacroFlow Extension → Send Request to Macro Workspace.
<img width="858" height="530" alt="rightClick-send-to-MacroWorkspace1" src="https://github.com/user-attachments/assets/043f66f2-dcd0-4c03-8938-b7d400bf4097" />

When sending a request to the Macro Workspace, you can choose to create a new workflow or select an existing one.
<img width="739" height="447" alt="rightClick-send-to-MacroWorkspace2" src="https://github.com/user-attachments/assets/62dd6756-6eeb-4882-b7f0-c3a6e7ff1f0d" />

If creating a new workflow, enter a workflow name - the request will be added to that workflow automatically.
<img width="702" height="470" alt="rightClick-send-to-MacroWorkspace3" src="https://github.com/user-attachments/assets/9320e916-43aa-40fd-a147-abdc4bee5657" />

As shown below, the workflow is created and the Request Node is pre-populated with the request details.
<img width="1189" height="547" alt="rightClick-send-to-MacroWorkspace4" src="https://github.com/user-attachments/assets/6a3fa60a-dc8a-4b8e-8bb3-a5b9ca8116a5" />

---

**2. Ignite Node, Custom Node, Request Nodes & Append Request - Feature Demo**

A workflow is set up to grab a fresh token after login.
<img width="1181" height="654" alt="ignite1" src="https://github.com/user-attachments/assets/4c3cfc14-c363-466c-9bf8-fcd3a94dccc4" />


Instead of recreating the login flow in a new workflow, an Ignite Node is added. The screenshot below shows how the token placeholders captured by the "Login Flow1" workflow become available in the "IgniteNode Demo" workflow.
<img width="1278" height="690" alt="ignite2" src="https://github.com/user-attachments/assets/5b8eaa6d-5514-4c26-a181-b4ef4b814f3c" />


A Custom Node is used here to capture user input so that it can be used to validate how the input is reflected across other endpoints.
<img width="1328" height="707" alt="ignite3" src="https://github.com/user-attachments/assets/46b32893-7908-47b4-91ed-ebcb95290ee0" />


A request is made to the "Add New Coupon Code" endpoint while the workflow is OFF - as shown in the screenshot, the request is unauthorized due to missing tokens.
<img width="1170" height="386" alt="ignite4" src="https://github.com/user-attachments/assets/a3e50d83-f785-4e66-913e-6049babfb928" />

The workflow is turned ON.
<img width="1202" height="654" alt="ignite5" src="https://github.com/user-attachments/assets/8f54b6f1-9ec4-4e83-8f4c-d28bb74b6fe0" />

The workflow executes and the request succeeds.
<img width="1165" height="443" alt="ignite6" src="https://github.com/user-attachments/assets/700ae9d3-2f3f-4a56-a434-a7dcc0f1452b" />

To validate how the newly added coupon data is reflected in another endpoint, make sure the "Add New Coupon Code" endpoint is set as the Trigger Target (TT). Then right-click the second endpoint (where the reflected data can be fetched) and select "Append Response to [Request Node Name]".
<img width="1250" height="708" alt="ignite7" src="https://github.com/user-attachments/assets/4d1257e7-3638-435b-b488-d900e6ab4f54" />
<img width="1189" height="685" alt="ignite8" src="https://github.com/user-attachments/assets/fb15d71d-4eb6-48f4-bbb5-a3c88289785c" />

Upon requesting the "Add New Coupon Code" endpoint again, the newly added coupon details are visible in the appended response - demonstrating how user input data is reflected across endpoints.
<img width="1162" height="680" alt="ignite9" src="https://github.com/user-attachments/assets/95e9b4b2-fc65-4b31-b43f-2a022650f86a" />


---
**3. Custom Node**

Trigger Node → Custom Node - In this demo, Scope filtering, Header Addition, Match & Replace, and Header Removal rules are configured on the Custom Node.
<img width="1549" height="683" alt="custom1" src="https://github.com/user-attachments/assets/e6acbae9-08d8-480c-8aff-4251066aceb8" />

Workflow OFF - the triggered request passes through unmodified; all unwanted headers are present in the outgoing request.
<img width="1173" height="647" alt="CustomBefore1" src="https://github.com/user-attachments/assets/3f76832c-1d4b-46be-9a11-f75b1bb1fd65" />

Workflow ON - the Custom Node applies its rules; headers are added, replaced, and removed as configured.
<img width="1157" height="647" alt="CustomAfter" src="https://github.com/user-attachments/assets/b02c5ae2-7c53-4ef0-8e38-b385988a23c5" />

---
**4. CSRF Token Automation**

As shown, the server responds with "Invalid CSRF token" - every request requires a fresh CSRF token.
<img width="1170" height="395" alt="csrf1" src="https://github.com/user-attachments/assets/a388dda3-8cc4-4599-ab48-8d58b4803597" />

A workflow is created with a Request Node that fetches the CSRF token, and a Custom Node that extracts the token value and passes it downstream via the Downstream Captures section.
<img width="1131" height="681" alt="csrf2" src="https://github.com/user-attachments/assets/278c6c3e-1d2b-4786-85bf-89654cb49db4" />
<img width="1579" height="763" alt="csrf3" src="https://github.com/user-attachments/assets/032da8e2-3382-49a3-8d51-b56546ca1cc0" />

Workflow ON - the same request is made again; this time the correct CSRF token is injected automatically and the request is successfully redirected to the expected location.
<img width="1132" height="720" alt="csrf4" src="https://github.com/user-attachments/assets/2afbdfe5-eb23-4734-be89-9b32a63ef8b1" />
<img width="1168" height="441" alt="csrf5" src="https://github.com/user-attachments/assets/aa3a828a-d6b6-4fc8-9dfe-aa76bac67fd4" />


---

### Core use-cases

**Session refresh / login flow** - Connect a Trigger node to a sequence of Request nodes that perform a fresh login and extract a session token. Any Burp tool request that matches the trigger endpoint automatically gets the new session injected before it is forwarded.

**Multi-step pre-chain** - To reach `api3` you first need a value from `api1` and a CSRF token from `api2`. Wire those Request nodes together. The extension resolves dependency order and executes the full chain before the live request goes out.

**Payload reflection detection** - Intruder, Repeater, or Audit Scan injects a payload into `api1`. If that payload is reflected in any downstream response, the Custom Node match rules fire and flag a finding.

**Sub-workflow composition** - Use Ignite nodes to call another saved workflow as a named sub-step, forwarding selected captures between workflows.


---

## Installation

1. Download `MacroWorkFlow-v1.0.0.jar` from the [Releases](#) page.
2. In Burp Suite go to **Extensions → Add**.
3. Select **Extension type: Java**, browse to `MacroWorkFlow-v1.0.0.jar`, click **Next**.
4. The **Macro Builder** tab appears in the Burp tab bar.

**Requirements:** Burp Suite Pro or Community 2023.x+, Java 11+.

---

## Available node types

| Node | Color | Purpose |
|---|---|---|
| Trigger | Dark header | Entry point - watches tool traffic and fires the workflow |
| Request | Blue/teal header | A single HTTP request/response with optional regex captures |
| Custom | Red header | Applies transformation, match/replace, code, and scope rules to live requests |
| Ignite | Orange header | Calls another saved workflow as an inline sub-step |

---

## Node reference

### Trigger Node

The entry point of every workflow. Defines which tool traffic activates the workflow.

**Settings:**
- **Repeater / Intruder / Proxy / Audit Scan / Extensions** - checkboxes select which Burp tools can fire the trigger.
- **Proxy through** - route trigger-driven requests through Burp's proxy listener.
- **Path matching** - the live request path must match the designated trigger target endpoint. Supports `$placeholder$` wildcards in the path.

One Trigger node per workflow. Connect it to one or more Request nodes.

---

### Request Node

Stores a single HTTP request template and extracts values from the response.

**Settings (node editor dialog):**
- **Request editor** - raw HTTP request with headers and body. Use `$placeholder$` tokens anywhere in the template; they are substituted at run-time with values captured upstream.
- **Response viewer** - shows the last received response (headers + body) after execution or a manual test send.
- **Captures (Received Data)** - regex patterns applied to the response. Each capture has a name and a `$placeholder$` label that downstream nodes can reference.
- **Captures (Sent Data)** - regex patterns applied to the outgoing request, useful for forwarding form values downstream.
- **Upstream Captures** - displays values captured by ancestor nodes in the chain. Use the pass-to-next checkboxes to forward selected values to child nodes.

**Canvas display:** shows the HTTP method, path, host, and any resolved capture values.

**Right-click options:** Edit, Send to Repeater, Send to Workflow, Set/Remove as Trigger Target, Toggle Append, Duplicate, Delete.

---

### Custom Node

Applies a configurable rule set to every live request that flows through the workflow. Rules are evaluated in order.

**Rule tabs:**

**Scope** - restrict the node to specific hostnames or URL paths (regex). Requests not matching the scope are passed through unchanged.

**Extension filter** - restrict to specific file extensions (e.g. `.js`, `.css`).

**Headers** - add, remove, or replace request or response headers. Supports `$placeholder$` tokens.

**Body conversion** - convert the request body between content types (form-encoded ↔ JSON, multipart, raw, etc.).

**Match & Replace** - regex find-and-replace on request or response body/headers. Patterns can include `$placeholder$` tokens that are resolved from upstream captures before matching.

**Code rules** - execute JavaScript or Python (JSR-223) against the live request. The `liveRequest` object is pre-populated with the current request bytes; upstream captures are available as named variables. Print output appears in the node log.

**Custom Checks** - select downstream Request nodes as reflection targets. If a payload injected by Intruder, Repeater, or an Audit scan appears in a target node's response, the match is recorded as a finding.

**Priority filters** - set an execution priority and tool filter so rules only apply to specific sources (e.g. only Intruder requests, not Repeater).

**Downstream Captures** - regex patterns applied to the live request or response to extract values and pass them forward as `$placeholder$` variables to child nodes.

**Testing Ground tab** - send a real HTTP request from within the editor to validate your rules and headers before saving.

---

### Ignite Node

Embeds another saved workflow as an inline sub-call within the current workflow.

**Settings:**
- **Workflow selector** - choose one or more workflows to invoke, in order.
- **Capture selector** - choose which captures from the referenced workflow(s) to forward as named `$placeholder$` variables into the current workflow.
- **Stale warning** - a `⚠` badge appears on the node card if the referenced workflow's captures have changed since this Ignite node was last saved.
- **Show details / Show captures** - toggle inline summary display on the node card.

Ignite nodes connect via a blue circle port (not the diamond). They sit between a Trigger node and downstream Request or Custom nodes.

---

## Workflow execution

When a live Burp request matches a Trigger node's endpoint and tool filter:

1. **Pre-chain** - the extension reverse-BFS walks from the Trigger Target node back to the Trigger to identify all ancestor Request nodes. These execute in dependency order, collecting capture values.
2. **Placeholder injection** - captured values are substituted into the live request before it is forwarded to Burp.
3. **Custom rule application** - Custom Node rules are applied to the substituted live request.
4. **Trigger Append** - if any Request nodes are flagged as Append nodes, their responses are collected and merged into the trigger target's response for downstream validation.
5. **BFS execution** - remaining descendant nodes execute breadth-first, each receiving upstream captures from their ancestors.
6. **Findings** - if a Custom Check match rule detects a reflected payload in any execution node's response, the match is recorded in the Workflow Log.

The **Workflow Log** panel at the bottom of the canvas shows real-time execution status, node-by-node results, and any findings raised.

---

## Workflow Player

Click **▶ Play** on any workflow in the list to open the Workflow Player - a graph panel that re-runs the entire workflow manually, with live status badges (pending / running / done / error) on each node as execution progresses.

---

## Canvas controls

| Action | How |
|---|---|
| Add node | Right-click canvas → Add Node |
| Connect nodes | Drag from an output port (circle or diamond) to another node's input |
| Move node | Click and drag the node card |
| Resize node | Drag the bottom-right handle of any node card |
| Multi-select | Hold Shift + click, or drag a selection rectangle |
| Copy / Paste | Ctrl+C / Ctrl+V (configurable in User Preferences) |
| Delete | Delete key, or right-click → Delete |
| Zoom | Scroll wheel |
| Pan | Click and drag on empty canvas space |
| Freeze canvas | Toolbar **Freeze** toggle - freezes node positions so pan does not move nodes |
| Rename workflow | Double-click the workflow name in the left panel |
| Enable / disable workflow | Toggle checkbox next to the workflow name in the list |
| Export workflow | Right-click workflow → Export JSON |
| Import collection | Right-click canvas → Import Collection (Postman, Insomnia, Swagger/OpenAPI) |

---

## Themes

Three themes available from the toolbar or **Settings → User Preferences**:

- **Dark** (default) - dark background, light text.
- **Light** - white background, dark text.
- **Darker** - dark background with green accent text.

Theme applies to the canvas, all node cards, and all editor dialogs.

---

## Workflow JSON format

Workflows are stored as JSON and can be exported/imported. Each workflow has a `macroName`, an `enabled` flag, and a `nodes` array.

```json
[
  {
    "macroName": "LoginAndAccess",
    "enabled": true,
    "nodes": [
      {
        "id": "<uuid>",
        "name": "Trigger",
        "isStartNode": true,
        "applyRepeater": true,
        "applyScanner": true,
        "targets": ["<request-node-uuid>"]
      },
      {
        "id": "<request-node-uuid>",
        "name": "Login",
        "nodeType": "request",
        "hostName": "example.com",
        "port": 443,
        "https": true,
        "requestDetails": "POST /login HTTP/1.1\r\nHost: example.com\r\n\r\nuser=admin&pass=admin",
        "captures": [
          {
            "id": "session",
            "regex": "Set-Cookie: session=([^;]+)",
            "placeholder": "$session$",
            "active": true
          }
        ],
        "targets": ["<next-node-uuid>"]
      }
    ]
  }
]
```

`nodeType` values in this build: `request`, `ignite`, `custom` (Trigger nodes use `isStartNode: true`).

---

## License

For authorized penetration testing use only. Do not use against systems you do not have explicit permission to test.


## ☕ Buy Me a Coffee

If you find my work useful, consider supporting me!

| | Network | Address |
|---|---|---|
| [![BTC](https://img.shields.io/badge/BTC-orange?logo=bitcoin&logoColor=white)](https://mempool.space/address/bc1p2pwjyk64e6sm89pn9ksy3w4u8tmsxyfvfr4lpl9hjtememjzwv0qm55sq4) | Bitcoin Taproot | `bc1p2pwjyk64e6sm89pn9ksy3w4u8tmsxyfvfr4lpl9hjtememjzwv0qm55sq4` |
| [![ETH](https://img.shields.io/badge/ETH-blue?logo=ethereum&logoColor=white)](https://etherscan.io/address/0x6b36e02F7557D19C7D443fd7b5F7f4a45056e8A6) | Ethereum | `0x6b36e02F7557D19C7D443fd7b5F7f4a45056e8A6` |
| [![DOGE](https://img.shields.io/badge/DOGE-yellow?logo=dogecoin&logoColor=white)](https://dogechain.info/address/DDRqpMn3KfAQAkxUaSxWWAV4uNn5HCX5Qh) | Dogecoin | `DDRqpMn3KfAQAkxUaSxWWAV4uNn5HCX5Qh` |
