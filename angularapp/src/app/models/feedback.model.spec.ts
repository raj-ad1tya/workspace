import { Feedback } from './feedback.model';

describe('Feedback Model', () => {

  fit('frontend_Feedback_model_should_create_an_instance', () => {
    // Create a sample Feedback object
    const feedback: Feedback = {
      feedbackId: 1,
      userId: 456,
      comments: 'Excellent service!',
      date: new Date('2024-07-10'),
      busId: 101,
      rating: 5
    };


   expect(feedback).toBeTruthy();
   expect(feedback.comments).toBe('Excellent service!');
   
  });

});
