#include <Servo.h> 
#include <SPI.h>
#include <MFRC522.h>
#include <ArduinoJson.h>

#define SERVO 8 // Porta Digital 6 PWM  
#define SS_PIN 10
#define RST_PIN 9
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance.
 
char st[20];

Servo s; // Variável Servo
int pos; // Posição Servo
const int tamanho = 50;
 
void setup() 
{
  s.attach(SERVO);
  Serial.begin(9600);   // Inicia a serial
  s.write(0); // Inicia motor posição zero  
  SPI.begin();      // Inicia  SPI bus
  mfrc522.PCD_Init();   // Inicia MFRC522
}
 
void loop() 
{
  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) 
  {
    return;
  }
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) 
  {
    return;
  }
  //Mostra UID na serial
  String conteudo= "";
  byte letra;
  for (byte i = 0; i < mfrc522.uid.size; i++) 
  {
     conteudo.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
     conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  conteudo.toUpperCase();
  String tagid = conteudo.substring(1); 
  if (tagid == "86 AF 41 83" || tagid == "C2 DF 5E 30") //UID 1 - Chaveiro
  {
    StaticJsonDocument<tamanho> json;
    json["tagid"] = conteudo.substring(1);
    json["status"] = pos;
    serializeJson(json, Serial);    
    Serial.println();
    delay(200);
    if(pos == 0 || pos == -1)
    {
      for(pos = 0; pos < 90; pos++)
      {
        s.write(pos);
        delay(10);
      }
    }
    else
    {
      for(pos = 90; pos >= 0; pos--)
      {
        s.write(pos);
        delay(10);
      } 
    }
  }
}
