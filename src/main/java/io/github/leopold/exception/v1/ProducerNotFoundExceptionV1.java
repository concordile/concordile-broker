package io.github.leopold.exception.v1;

public class ProducerNotFoundExceptionV1 extends BaseNotFoundExceptionV1 {

    public ProducerNotFoundExceptionV1(String producerName) {
        super("Producer not found by name: '" + producerName + "'");
    }

}
