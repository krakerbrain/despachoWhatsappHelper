# Despacho WhatsApp Helper

Despacho WhatsApp Helper es una aplicación Android diseñada para facilitar el envío de mensajes de WhatsApp cuando se reciben despachos de Boosmap. La aplicación permite dictar números de guía usando el reconocimiento de voz, formateándolos automáticamente en grupos de nueve dígitos y copiando el resultado al portapapeles junto con tu número de teléfono. Esto evita tener que corregir manualmente la entrada de voz, ahorrando tiempo y esfuerzo.

## Características

- **Reconocimiento de Voz**: Usa el reconocimiento de voz para dictar números de guía.
- **Formateo Automático**: Agrupa automáticamente los números en bloques de nueve dígitos.
- **Copia al Portapapeles**: Copia el texto formateado al portapapeles para pegar fácilmente en WhatsApp.
- **Interfaz Sencilla**: Permite ingresar el nombre una sola vez para incluirlo en cada mensaje.

## Motivación

La motivación para desarrollar esta aplicación surgió de la necesidad de agilizar el proceso de envío de mensajes en WhatsApp cuando se reciben los despachos de Boosmap. Utilizar el teclado de voz del teléfono solía ser ineficiente, ya que separaba los números dictados (por ejemplo, "13 74 87 365"), lo que requería edición manual para corregirlos.

Esta miniaplicación resuelve este problema al procesar automáticamente los números dictados y dejar el mensaje listo para pegarlo en un grupo de WhatsApp.

## Estado Actual del Desarrollo

Actualmente, la aplicación está en desarrollo y la funcionalidad para enviar mensajes automáticamente a un grupo de WhatsApp específico no ha sido implementada. Esto se debe a que aún no se ha definido el número del grupo de WhatsApp al cual se deberían enviar los números.

### Características Planeadas

- **Envío Automático**: Planeamos implementar una función para enviar automáticamente el mensaje al número del grupo de WhatsApp una vez que esté disponible.

### Notas

- **Campo de Número**: El campo para ingresar el número de teléfono es por ahora innecesario, ya que la función de envío automático aún no está activa. Se mantendrá en la interfaz para su uso futuro.

## Instalación

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en Android Studio.
3. Conecta un dispositivo Android o inicia un emulador.
4. Ejecuta la aplicación.

## Uso

1. Presiona botón Editar
1. Llena los campos correspondientes.
2. Presiona el botón "Hablar" y dicta los números de guía.
3. La aplicación formateará los números y los copiará al portapapeles junto con tu nombre.
4. Ve a WhatsApp y pega el mensaje en el grupo correspondiente.

## Tecnologías Utilizadas

- Kotlin
- Android SDK
- Reconocimiento de voz de Android

## Contribución

Si deseas contribuir a este proyecto, por favor abre un *issue* o envía un *pull request*.

