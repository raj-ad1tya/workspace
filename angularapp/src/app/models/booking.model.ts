import { Bus } from "./bus.model";
import { User } from "./user.model";

export interface Booking {
    bookingId?: number;
    user: User;
    bus: Bus;
    numberOfSeats: number;
    bookingDate: string;
    seatNumbers?: number[];
    status: string;
}
