# MacroFlow - Burp Suite Extension (Version 1.0.1)

---

## What is MacroFlow?

MacroFlow is a visual, node-based macro workflow engine for Burp Suite.
It replaces Burp's built-in Macro system with a drag-and-drop canvas where you
build multi-step HTTP flows, automatically inject session tokens, and validate
reflected payloads across multiple (MAX 3) targets - all without writing a single script.

<img width="1309" height="722" alt="Screenshot 2026-05-30 002621" src="https://github.com/user-attachments/assets/42868dce-31c4-4123-b20b-0fe662043c43" />



## What it does

Macro Builder adds a **"Macro Builder"** tab to Burp Suite. Inside it you get a canvas where you connect nodes into workflows. Each workflow describes a chain of HTTP actions that fires automatically when Burp tools (Repeater, Intruder, Proxy, Scanner, other extensions) send a matching request.

---
## Demo

The demo.json file includes the following workflows:
- Workflows from **crAPI** (https://github.com/OWASP/crAPI)
- A **CSRF** workflow based on the login flow from **Gin and Juice Shop** (https://ginandjuice.shop)

**Import**
<img width="1264" height="555" alt="Import1" src="https://github.com/user-attachments/assets/bda9eae4-721a-4886-859d-4001bcaf0e93" />

---
**Add Captures**

1. Search Box in Node View & Node Edit Window - the Node View and Node Edit windows for easier navigation and filtering.
<img width="921" height="598" alt="AddCaptures7" src="https://github.com/user-attachments/assets/ac0fdd87-fd1d-46cb-90af-bcbeaf9719dc" />

2. Add Capture from Selection (Right-Click Context Menu): Right-clicking and selecting "Add Capture from Selection" now opens a popup window where you can:
- Define your own capture pattern
- See a live match count for your Regex against the current HTTP data
<img width="1217" height="756" alt="AddCaptures4" src="https://github.com/user-attachments/assets/110f7792-dac2-454f-abf2-d346c00835f2" />
<img width="1176" height="730" alt="AddCaptures5" src="https://github.com/user-attachments/assets/e8245cc4-83ed-4b0d-b6dc-af7ea5711844" />
<img width="1174" height="762" alt="AddCaptures6" src="https://github.com/user-attachments/assets/f1a4ddda-3460-4567-a1a1-f156238478ed" />



---
**1. Send to Macro Workspace**

Right-click any request → MacroFlow Extension → Send Request to Macro Workspace.
<img width="858" height="530" alt="rightClick-send-to-MacroWorkspace1" src="https://github.com/user-attachments/assets/409ed0f5-9db6-417d-87e9-6ff69af73f52" />


When sending a request to the Macro Workspace, you can choose to create a new workflow or select an existing one.
<img width="739" height="447" alt="rightClick-send-to-MacroWorkspace2" src="https://github.com/user-attachments/assets/1aaf93a2-8b61-495e-bee4-9dbc27edc592" />


If creating a new workflow, enter a workflow name - the request will be added to that workflow automatically.
<img width="702" height="470" alt="rightClick-send-to-MacroWorkspace3" src="https://github.com/user-attachments/assets/37685072-d63d-482b-b1c2-cd58a6a79966" />


As shown below, the workflow is created and the Request Node is pre-populated with the request details.
<img width="1189" height="547" alt="rightClick-send-to-MacroWorkspace4" src="https://github.com/user-attachments/assets/874d3cdd-90ab-478b-8e34-1f3297e3ea8c" />


---

**2. Ignite Node, Custom Node, Request Nodes & Append Request - Feature Demo**

A workflow is set up to grab a fresh token after login.
<img width="1181" height="654" alt="ignite1" src="https://github.com/user-attachments/assets/23dc547b-2364-45fb-a56d-cd3ad73e5938" />


Instead of recreating the login flow in a new workflow, an Ignite Node is added. The screenshot below shows how the token placeholders captured by the "Login Flow1" workflow become available in the "IgniteNode Demo" workflow.
<img width="1278" height="690" alt="ignite2" src="https://github.com/user-attachments/assets/6bb4fdcf-521d-40e0-a979-045e1fe8ab91" />


A Custom Node is used here to capture user input so that it can be used to validate how the input is reflected across other endpoints.
<img width="1328" height="707" alt="ignite3" src="https://github.com/user-attachments/assets/b6481095-b65d-40c4-8786-b187bbbd467a" />


A request is made to the "Add New Coupon Code" endpoint while the workflow is OFF - as shown in the screenshot, the request is unauthorized due to missing tokens.
<img width="1170" height="386" alt="ignite4" src="https://github.com/user-attachments/assets/644a8310-065b-4afe-959c-c9d51adceff4" />

The workflow is turned ON.
<img width="1202" height="654" alt="ignite5" src="https://github.com/user-attachments/assets/7416255d-8a61-4662-8446-84a24359f46c" />

The workflow executes and the request succeeds.
<img width="1165" height="443" alt="ignite6" src="https://github.com/user-attachments/assets/5b50cdb4-6a7f-4976-886c-74917eac4933" />

To validate how the newly added coupon data is reflected in another endpoint, make sure the "Add New Coupon Code" endpoint is set as the Trigger Target (TT). Then right-click the second endpoint (where the reflected data can be fetched) and select "Append Response to [Request Node Name]".
<img width="1250" height="708" alt="ignite7" src="https://github.com/user-attachments/assets/1630d990-3693-4696-b962-62106128bb74" />
<img width="1189" height="685" alt="ignite8" src="https://github.com/user-attachments/assets/eadc0f86-8c2a-4cae-91ad-8c0cada8941d" />

Upon requesting the "Add New Coupon Code" endpoint again, the newly added coupon details are visible in the appended response - demonstrating how user input data is reflected across endpoints.
<img width="1162" height="680" alt="ignite9" src="https://github.com/user-attachments/assets/22dd3725-3efc-4cb0-ab61-5fd7824660ed" />


---
**3. Custom Node**

Trigger Node → Custom Node - In this demo, Scope filtering, Header Addition, Match & Replace, and Header Removal rules are configured on the Custom Node.
<img width="1549" height="683" alt="custom1" src="https://github.com/user-attachments/assets/e816d535-ca17-4070-8570-39906dd85681" />

Workflow OFF - the triggered request passes through unmodified; all unwanted headers are present in the outgoing request.
<img width="1173" height="647" alt="CustomBefore1" src="https://github.com/user-attachments/assets/4387a1b4-a259-4f7a-ba5b-75209ad5be6d" />

Workflow ON - the Custom Node applies its rules; headers are added, replaced, and removed as configured.
<img width="1157" height="647" alt="CustomAfter" src="https://github.com/user-attachments/assets/71837ec0-c096-49e1-97b6-6f483563c8b1" />


---
**4. CSRF Token Automation**

As shown, the server responds with "Invalid CSRF token" - every request requires a fresh CSRF token.
<img width="1170" height="395" alt="csrf1" src="https://github.com/user-attachments/assets/094d4b2f-a2ca-4ddd-88ea-a3507bce03fc" />

A workflow is created with a Request Node that fetches the CSRF token, and a Custom Node that extracts the token value and passes it downstream via the Downstream Captures section.
<img width="1131" height="681" alt="csrf2" src="https://github.com/user-attachments/assets/23da09c6-42ec-4fbf-8f1d-d4cd50c2ac95" />
<img width="1579" height="763" alt="csrf3" src="https://github.com/user-attachments/assets/15137e23-32e4-4fed-ab1d-ad1f4a8fb458" />

Workflow ON - the same request is made again; this time the correct CSRF token is injected automatically and the request is successfully redirected to the expected location.
<img width="1132" height="720" alt="csrf4" src="https://github.com/user-attachments/assets/4597ab27-a91a-4d58-a945-47196f1d4428" />
<img width="1168" height="441" alt="csrf5" src="https://github.com/user-attachments/assets/4b0a73e5-daa6-47e2-8ebb-d9ef717c5919" />


---

### Core use-cases

**Session refresh / login flow** - Connect a Trigger node to a sequence of Request nodes that perform a fresh login and extract a session token. Any Burp tool request that matches the trigger endpoint automatically gets the new session injected before it is forwarded.

**Multi-step pre-chain** - To reach `api3` you first need a value from `api1` and a CSRF token from `api2`. Wire those Request nodes together. The extension resolves dependency order and executes the full chain before the live request goes out.

**Payload reflection detection** - Intruder, Repeater, or Audit Scan injects a payload into `api1`. If that payload is reflected in any downstream response, the Custom Node match rules fire and flag a finding.

**Sub-workflow composition** - Use Ignite nodes to call another saved workflow as a named sub-step, forwarding selected captures between workflows.


---

## Installation

1. Download `MacroWorkFlow.jar` from the [Releases](#) page.
2. In Burp Suite go to **Extensions → Add**.
3. Select **Extension type: Java**, browse to `MacroWorkFlow.jar`, click **Next**.
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
