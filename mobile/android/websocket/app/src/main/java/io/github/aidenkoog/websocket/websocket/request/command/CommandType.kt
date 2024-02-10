package io.github.aidenkoog.websocket.websocket.request.command

enum class CommandType {
    CLEAN,
    DEVICE_STATUS,
    ROBOT_SETTING_DATA,
    SEND_ROBOT_STATUS,
    MOTION,
    ON_OFF,
    TEST_START, TEST, TEST_FINISH,
    OPERATION_SETTING_DATA,
    JOINT_TEMP,
    ICECREAM_COMPLETE,
}