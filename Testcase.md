# Test Cases
## Function Test Cases
Click on the Write article button. The text area, my avatar and the Post button should be shown correctly. 

1. Do not input text in the text area and there is no uploaded file. The Post button should be gray and disabled.
2. Input some text(less than maximum size) in the text area. Do not upload file. Click on the Post button. The text can be shown correctly.
3. Input some text(less than maximum size) in the text area. Upload a text file(less than maximum file size). Clicking on the Post button. The text can be shown correctly. The text file can be shown as an icon. And it can be downloaded correctly.
4. Do not input text in the text area. Upload a text file(less than maximum file size). Click on the Post button. The text file can be shown as an icon. And it can be downloaded correctly.
5. Input some text(more than maximum size) in the text area. Cannot input more text after the maximum size(e.g., 4000 characters).
6. Input some text(less than maximum size) in the text area. Upload a text file whose file size is more than the maximum size. There should be an error that tells the user the file is too big.
7. Input some text(less than maximum size) in the text area. Upload a text file whose file size is the maximum size. Clicking on the Post button. The file should be uploaded successfully.
8. Input some text(less than maximum size) in the text area. Drag and drop a text file whose file size is the maximum size. Clicking on the Post button. The file should be uploaded successfully.
9. Input some text(less than maximum size) in the text area. Drag and drop an audio file(wav, mp3, flac, etc.) whose file size is less than the maximum size. Click on the Post button. The file should be uploaded successfully. The audio file can be shown as an icon. And it can be played correctly.
10. Input some text(less than maximum size) in the text area. Drag and drop a video file(mp4, avi, mov, etc.) whose file size is less than the maximum size. Click on the Post button. The file should be uploaded successfully. The video file can be shown as an icon. And it can be played correctly.
11. Do not input text in the text area. Upload a text file(less than maximum file size). Upload another file and the old uploaded file should be replaced with the new uploaded file. Click on the Post button. The file should be uploaded successfully.
12. Input some text(less than maximum size) in the text area. Upload a text file(less than maximum file size). Drag and drop another file. the old uploaded file should be replaced with the new file. Click on the Post button. The file should be uploaded successfully.
13. Input multi-byte characters(e.g., Chinese characters). Click on the Post button. The Chinese character can be shown correctly.
14. Input emoji. Click on the Post button. The emoji can be shown correctly.
15. Input sentence with line break. Click on the Post button. The line break can be shown correctly.
16. Input some text(less than maximum size) in the text area. Use hotkey to trigger the Post button. The text can be shown correctly.
17. Input some text(less than maximum size) in the text area. Make the network disconnected. Click on the Post button. There is an error about the broken network after timeout.
18. Input some text(less than maximum size) in the text area. Upload a text file(file size is zero). Click on the Post button. There should be error that not allow zero size file.


## Security Test Cases
1. SQL Injection Testing. Try entering SQL injection payloads into the text area and observe the application's response. For example: `' OR '1'='1' --`
2. Cross-Site Scripting (XSS) Testing. Attempt to inject script tags or other HTML/JavaScript code into the text area. For example: `<script>alert('XSS')</script>`
3. Cross-Site Request Forgery (CSRF) Testing. Use ZAP to identify common CSRF vulnerabilities and provide insights into potential areas of concern.
4. Content Spoofing. If the content is shown based on URL parameters, change the parameter and check if we can post content as another user.
5. File Upload Testing. Upload a file that is executable. Make sure it cannot be executed.
6. Security Headers. Ensure that the application sets proper security headers, such as Content Security Policy (CSP) headers, to mitigate potential security risks.
7. Input Encoding. Verify that the application properly encodes and sanitizes user inputs to prevent script injection attacks. For example, `javascript:alert('XSS')`, `onmouseover='alert("XSS")'`, `<img src=x onerror=alert('XSS')>`.
8. Session Management. Make sure the user is logout after some time without any actions. 

## Performance Test Cases
### Backend
Use performance testing tools such as Jmeter to verify the performance metrics comply with the specification.

1. Response time and throughout when concurrent users post text.
2. Response time and throughout when concurrent users upload file.
3. Heavy load testing. Simulate a large number of virtual users continuously post text and/or upload files. Gradually increase the load. Measure response time, throughput, and resource utilization.
4. Input with large data. Measure the system's response time and check for any degradation. Assess the impact on performance with increased input size.
5. Long duration. Continuously input text and/or upload file for an extended duration (e.g., several hours). Monitor the system for memory leaks or performance degradation over time. Measure and assess the long-term stability of the application.

### Frontend
User Chrome dev tools or similar tools to measure the performance of the frontend.
1. UI render time when the UI is visited at the first time.
2. UI render time when the UI is visited after the first time.
3. Static resources load time, such as the avatar image.

## Globalization Test Cases
It depends on which languages that we support. For example, we support Simplified Chinese and French besides English.

1. Client OS is Simplified Chinese language. Location is China. The browser preferred language is Simplified Chinese. The UI should be shown in Simplified Chinese.
2. Client OS is French. Location is France. The browser preferred language is French. The UI should be shown in French.
3. Client OS is French. Location is France. The browser preferred language is Simplified Chinese. The UI should be shown in Simplified Chinese.

## Compatibility Test Cases
Test the matrix of the following OS and browsers combination.

1. OS: Windows, MacOS, Linux
2. Browser: Chrome, Firefox, Edge, Safari

Test different UI resolutions of the browser to make sure the responsiveness of the frontend UI.

## Accessibility Test  Cases
Use tools such as axe to test the Accessibility.
