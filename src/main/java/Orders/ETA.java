package Orders;

import java.time.LocalDateTime;

public class ETA
{
    private LocalDateTime delivery_time;

    public ETA() {
        super();
    }

    public ETA(LocalDateTime delivery_time) {
        this.delivery_time = delivery_time;
    }

    public LocalDateTime getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(LocalDateTime delivery_time) {
        this.delivery_time = delivery_time;
    }


}

