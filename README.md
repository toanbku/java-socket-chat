## MẠNG MÁY TÍNH (CO3003)

## Bài tập lớn 1

# Simple Chat Application

```
GVHD: Nguyễn Hồng Nam
```
```
SV thực hiện: 
   [Hide]
   [Hide]
   [Hide]
   [Hide]
```
```
Tp. Hồ Chí Minh, Tháng 10/2018
```

## Mục lục


- 1 Giới thiệu ứng dụng
   - 1.1 Login
   - 1.2 Chat riêng tư
   - 1.3 Truyền gửi file
- 2 Định nghĩa giao thức cho từng chức năng
- 3 Thiết kế ứng dụng
   - 3.1 Công nghệ sử dụng
   - 3.2 Kiến trúc ứng dụng
   - 3.3 Các class chính
- 4 Đánh giá kết quả hiện thực
   - 4.1 Kết quả đạt được
   - 4.2 Những điều chưa đạt được

## 1 Giới thiệu ứng dụng

Ứng dụng là phần mềm cho phép hai hay nhiều người dùng có thể giao tiếp với nhau với các
tính năng chính: login, tạo chat riêng tư, truyền gửi file trong lúc chat.

### 1.1 Login

Chức năng này cho phép người dùng đăng nhập vào ứng dụng khi biết IP và port của server.

### 1.2 Chat riêng tư

Chức năng này cho phép user tạo hội thoại riêng tư và gửi tin trực tiếp tới user khác. Một user
có thể nhắn tin trực tiếp với nhiều user trong cùng một thời điểm.

### 1.3 Truyền gửi file

Chức năng này cho phép user gửi và nhận một số file với định dạng và kích thước được quy định
trước bởi nhà phát triển trong chat riêng tư với user khác khi được cho phép.


## 2 Định nghĩa giao thức cho từng chức năng

|Nội dung  | Mục đích      |Ghi chú|
|----------|:-------------:|------:|
|<SESSION_REQ>clientID</SESSION_REQ> |Yêu cầu tạo một chat session từ client. clientID là id của người dùng|Sử dụng 1 lần cho mỗi chat session |
|<PEER_NAME>userName</PEER_NAME>| Tạo user khi người dùng đăng nhập vào server | userName là tên người dùng|
|<PORT>numPort</PORT>| Tạo port cho user| numPort là số hiệu port|
|<SESSION_KEEP_ALIVE></SESSION_KEEP_ALIVE>|Nội dung request do user gửi lên server | User request 10s/lần bao gồm tên user và trạng thái|
|<STATUS>statusUser</STATUS>| Thông báo trạng thái user| statusUser là trạng thái user|
|<SESSION_DENY />| Từ chối người dùng kết nối tới server| Từ chối sau khi người dùng login|
|<SESSION_ACCEPT></SESSION_ACCEPT>| Chấp nhận người dùng kết nối tới server | Chấp nhận sau khi người dùng login|
|<CHAT_REQ>userName</CHAT_REQ>| Yêu cầu tạo chat riêng tư  từ một user với user khác | userName là tên user tạo yêu cầu chat riêng tư|
|<IP>stringIP</IP>|Định danh IP cho user| stringIP là IP của user|
|<CHAT_DENY />| Từ chối tạo chat session| Từ chối sau khi có yêu cầu tạo chat riêng tư|
|<CHAT_ACCEPT />| Chấp nhận tạo chat session| Chấp nhận sau khi có yêu cầu tạo chat riêng tư|
|<CHAT_MSG> Message</CHAT_MSG>|Gửi nội dung chat|Message được gửi khi user đồng ý tạo chat riêng tư
|<PEER> peerData </PEER>|Xác định peer của user do server trả về |peerData là peer của user gồm name, port, IP|
|<FILE_REQ> fileName</FILE_REQ>|Yêu cầu gửi file |fileName là tên file muốn gửi|
|<FILE_REQ_NOACK />| Từ chối yêu cầu gửi file |Sử dụng sau khi có yêu cầu gửi file|
|<FILE_REQ_ACK></FILE_REQ_ACK>| Chấp nhận yêu cầu gửi file| Sử dụng sau khi có yêu cầu gửi file|
|<FILE_DATA_BEGIN />| Bắt đầu gửi file| Quá trình gửi file sau khi người gửi và nhận đồng ý|
|<FILE_DATA>fileData</FILE_DATA>| Nội dung file cần chuyển|
|<FILE_DATA_END />| Kết thúc quá trình gửi file| Yêu cầu bởi người gửi file|
|<CHAT_CLOSE />|Kết thúc chat riêng tư| Sử dụng sau khi tạo chat riêng tư|
|MAX_MSG_SIZE| 102400 |Kích thước tối đa của một message(file) trong chat|
|SERVER_ONLINE| "RUNNING"| User đang online|
|SERVER_OFFLINE| "STOP"| User thoát khỏi chat session|

## 3 Thiết kế ứng dụng

### 3.1 Công nghệ sử dụng

* TCP Socket: Một kĩ thuật dùng để hỗ trợ lập trình các ứng dụng giao tiếp qua mạng. TCP
Socket sử dụng Stream để thực hiện quá trình truyền dữ liệu của hai máy tính đã thiết lập
kết nối.
* Java Swing: Là một phần của Java Foundation Classes (JFC) được sử dụng để tạo các ứng
dụng Window-Based.
### 3.2 Kiến trúc ứng dụng

### Form: Ứng dụng gồm bốn form chính:

* Server form: Form quản lí server, hiển thị thông tin server, các request user gửi lên.
* Login form: Form đăng nhập vào server. Để kết nối được, user phải cung cấp username
chưa có ai đăng kí trước đó với server.
* Main form: Form để quản lí danh sách những người dùng khác đang online cùng với user
hiện tại. User có thể chọn bất kì ai đang online để bắt đầu một cuộc trò chuyện.
* Chat form: Khi hai user đã chấp nhận trò chuyện với nhau thì form này sẽ xuất hiện. Hiển
thị thông tin cuộc trò chuyện giữa hai người.

### Flow Chart


Hình 1:Flowchart của ứng dụng


### 3.3 Các class chính
Hình 2:Class Diagram của ứng dụng

### Class cho server
* ServerGUI.java: sử dụng để thao tác với server như bật, tắt server. Ngoài ra còn hiển thị
các thông tin server như: IP, Port, số lượng người kết nối, các protocol mà client gửi lên.
* ServerCore.java: Đây là class để quản lí server, gồm đầy đủ thông tin, chức năng chính của
server:
    * Tạo một SocketServer.
    * Quản lí danh sách các user đang online
    * Tạo một thread là WaitForConnect để xử lý các request kết nối từ user. Có thể là yêu
    cầu dăng nhập, thoát ứng dụng, lấy thông tin user đang online. Nếu đăng nhập thành
    công, trả về cho client danh sách user đang online, nếu không sẽ trả về một protocol
    từ chối đăng nhập.

### Class cho client

* MainGUI.java: GUI class để hiển thị danh sách user khác đang online, bắt sự kiện gửi yêu
cầu chat của user.

* ChatGUI.java: Xử lí việc chat, gửi nhận File giữa hai user. Hiển thị nội dung chat giữa hai
user.

* ClientServer.java: Tạo và quản lí ServerSocket của mỗi user. Xử lí request chat gửi đến từ
user khác.
* Client.java: Quản lí thông tin hiện tại của user. Nó sẽ tạo một thread để liên tục gửi request
tới server để cập nhật danh sách user. Ngoài ra nó còn xử lí request chat tới user khác.

### Các class khác
* DataFile.java: Tạo một đối tượng File để gửi đi.
* Peer.java: Tạo một đối tượng Peer có các thuộc tính: IP, port, name.
* Tags.java: Định nghĩa các protocol được sử dụng trong ứng dụng.
* Encode.java: Định nghĩa các phương thức user đùng để gửi request lên server.
* Dedode.java: Giải mã các phương thức để lấy thông tin user, port hay ip...

## 4 Đánh giá kết quả hiện thực

### 4.1 Kết quả đạt được
* Ứng dụng được xây dựng dựa trên mô hình kết hợp giữa client-server cho việc quản lí các
user và P2P cho việc trò chuyện giữa hai user với nhau.
* Ứng dụng có các tính năng đơn giản như: chat giữa hai user, một lúc đồng thời chat với
nhiều user, gửi File trong quá trình chat.

### 4.2 Những điều chưa đạt được

* Mã nguồn còn chưa tối ưu cho ứng dụng.
* Ứng dụng còn có thể thêm các tính năng như: chat nhóm, gọi video...
