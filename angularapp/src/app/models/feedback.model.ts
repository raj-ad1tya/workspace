import { Bus } from "./bus.model";
import { User } from "./user.model";

export interface Feedback {
    feedbackId?: number;
    user?: User;
    userId: number;
    bus?: Bus;
    busId: number;
    rating: number;
    comments: string;
    date: Date;
}
