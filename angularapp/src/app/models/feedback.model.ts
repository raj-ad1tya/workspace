import { Bus } from "./bus.model";
import { User } from "./user.model";

export interface Feedback {
    feedbackId?: number;
    user: User;
    bus: Bus;
    comments: string;
    date: Date;
}
