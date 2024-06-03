import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
    // 메시지 본문을 byte[]로 가져오기
    byte[] body = message.getBody(byte[].class)

    String utf8String = new String(body, "UTF-8")

    // 문자열을 EUC-KR 인코딩의 byte[]로 변환
    byte[] eucKrBytes = utf8String.getBytes("EUC-KR")

    // EUC-KR 바이트 배열을 메시지에 설정하기 위해 ByteArrayOutputStream 사용
    ByteArrayOutputStream bos = new ByteArrayOutputStream()

    // 변환된 바이트 배열을 스트림에 작성
    bos.write(eucKrBytes, 0, eucKrBytes.length)
    bos.flush()

    // 변환된 본문을 메시지에 설정
    message.setBody(bos.toByteArray())
    
    return message;
}